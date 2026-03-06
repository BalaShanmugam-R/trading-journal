package com.trading.journal.transformer;

import com.trading.journal.entity.TradeRecordEntity;
import com.trading.journal.entity.TradeSummaryEntity;
import com.trading.journal.model.TradeRequest;
import com.trading.journal.model.TradeResponse;
import com.trading.journal.repository.TradeRecordRepository;
import com.trading.journal.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TradeTransformer {

    @Autowired
    TradeRecordRepository tradeRecordRepository;

    public TradeRecordEntity addTrade(TradeRequest request) {
        Double buy = calculateBuySellPrice(request, Constants.BUY);
        Double sell = calculateBuySellPrice(request, Constants.SELL);

        TradeRecordEntity record = TradeRecordEntity.builder().orderType(request.getOrderType() != null ? request.getOrderType() : Constants.MKT).exchange(request.getExchange() != null ? request.getExchange() : Constants.NSE).symbol(request.getSymbol() != null ? request.getSymbol() : Constants.NIFTY).instrument(request.getInstrument() != null ? request.getInstrument() : Constants.OPTIONS).productType(request.getProductType() != null ? request.getProductType() : Constants.INTRA_TRADE).strikePrice(request.getStrikePrice()).optionType(request.getOptionType() != null ? request.getOptionType() : Constants.CE).quantity(request.getQuantity() != null ? request.getQuantity() : 20).entryValue(request.getEntryValue()).exitValue(request.getExitValue()).targetValue(request.getTargetValue()).stopLossValue(request.getStopLossValue()).buyPrice(buy).sellPrice(sell).tradeDate(request.getTradeDate() != null ? request.getTradeDate() : LocalDate.now()).expiryDate(request.getExpiryDate() != null ? request.getExpiryDate() : LocalDate.now().plusDays(7)).build();

        return record;
    }

    private String calculateRiskReward(TradeRequest request) {
        Double entry = request.getEntryValue();
        Double slValue = request.getStopLossValue();
        Double targetValue = request.getTargetValue();

        // Convert SL to % (absolute value) & Convert Target to %
        Double slPercent = Math.abs(slValue / entry) * 100;  // 50/2500 = 2%
        Double targetPercent = Math.abs(targetValue / entry) * 100;  // 100/2500 = 4%

        // R:R = target% / sl%
        Integer ratio = (int) Math.round(targetPercent / slPercent);
        return String.format("1 : %s", String.valueOf(ratio));
    }

    private Integer calculateCapturedPoints(Double entry, Double exit) {
        return (int) Math.round(exit - entry);
    }

    private String status(Double profit) {
        boolean pl = profit > 0;
        if (pl) {
            return Constants.PROFIT_UPPERCASE;
        } else {
            return Constants.LOSS_UPPERCASE;
        }
    }

    private Double calculateBuySellPrice(TradeRequest request, String type) {
        Double pl = 0.0;
        if (type.equals(Constants.BUY)) {
            pl = request.getEntryValue() * request.getQuantity();
        } else if (type.equals(Constants.SELL)) {
            pl = request.getExitValue() * request.getQuantity();
        }
        return pl;
    }

    private Double calculateProfitLoss(Double buy, Double sell, String type) {
        Double pl = sell - buy;  // Total values

        if (Constants.PROFIT.equals(type)) {
            return pl > 0 ? pl : 0.0;
        } else if (Constants.LOSS.equals(type)) {
            return pl < 0 ? pl : 0.0;
        }
        return 0.0;
    }


    public TradeSummaryEntity addTradeSummary(TradeRequest request) {


        Double buy = calculateBuySellPrice(request, Constants.BUY);
        Double sell = calculateBuySellPrice(request, Constants.SELL);

        Double profit = calculateProfitLoss(buy, sell, Constants.PROFIT);
        Double loss = calculateProfitLoss(buy, sell, Constants.LOSS);
        return TradeSummaryEntity.builder().strikePrice(request.getStrikePrice()).optionType(request.getOptionType()).entryValue(request.getEntryValue()).exitValue(request.getExitValue()).targetValue(request.getTargetValue()).stopLossValue(request.getStopLossValue()).buyPrice(buy).sellPrice(sell).status(status(profit)).profit(profit).loss(loss).riskRewardRatio(calculateRiskReward(request)).capturedPoints(calculateCapturedPoints(request.getEntryValue(), request.getExitValue())).build();
    }

    public List<TradeResponse> getAllTradeRecords() {
        return tradeRecordRepository.findAllTradesWithSummary().stream().map(row -> TradeResponse.builder().exchange((String) row[0]).symbol((String) row[1]).instrument((String) row[2]).orderType((String) row[3]).quantity(((Number) row[4]).intValue()).buyPrice(((Number) row[5]).doubleValue()).sellPrice(((Number) row[6]).doubleValue()).strikePrice(((Number) row[7]).intValue()).optionType((String) row[8]).entryValue(((Number) row[9]).doubleValue()).exitValue(((Number) row[10]).doubleValue()).stopLossValue(((Number) row[11]).doubleValue()).targetValue(((Number) row[12]).doubleValue()).tradeDate((LocalDate) row[13]).expiryDate((LocalDate) row[14]).profit(((Number) row[15]).doubleValue()).loss(((Number) row[16]).doubleValue()).status((String) row[17]).riskRewardRatio((String) row[18]).capturedPoints(((Number) row[19]).intValue()).build()).collect(Collectors.toList());
    }

    public void updateTradeRecord(TradeRecordEntity entity, TradeRequest request) {
        entity.setExitValue(request.getExitValue());
        entity.setTargetValue(request.getTargetValue());
        entity.setStopLossValue(request.getStopLossValue());
    }

    public void updateTradeSummary(TradeRecordEntity entity, TradeSummaryEntity summary, TradeRequest request) {
        updateTradeRecord(entity, request);

        Double buy = summary.getBuyPrice(); //already bought
        Double sell = calculateBuySellPrice(request, Constants.SELL);

        Double profit = calculateProfitLoss(buy, sell, Constants.PROFIT);
        Double loss = calculateProfitLoss(buy, sell, Constants.LOSS);
        summary.setExitValue(request.getExitValue());
        summary.setTargetValue(request.getTargetValue());
        summary.setStopLossValue(request.getStopLossValue());
        summary.setSellPrice(sell);
        summary.setStatus(status(profit));
        summary.setProfit(profit);
        summary.setLoss(loss);
        summary.setRiskRewardRatio(calculateRiskReward(request));
        summary.setCapturedPoints(calculateCapturedPoints(summary.getEntryValue(), request.getExitValue()));
    }

    /*public List<TradeResponse> getAllTrades(List<TradeRecordEntity> recordEntityList, List<TradeSummaryEntity> summaryEntityList) {
        return recordEntityList.stream()
                .flatMap(record -> summaryEntityList.stream()
                        .filter(summary -> record.getTradeKey().equals(summary.getTradeKey()))
                        .map(summary -> mapRecord(record, summary)))
                .collect(Collectors.toList());
    }

    private static TradeResponse mapRecord(TradeRecordEntity record, TradeSummaryEntity summary) {
        return TradeResponse.builder()
                .exchange(record.getExchange())
                .symbol(record.getSymbol())
                .instrument(record.getInstrument())
                .orderType(record.getOrderType())
                .quantity(record.getQuantity())
                .buyPrice(record.getBuyPrice())
                .sellPrice(record.getSellPrice())
                .strikePrice(record.getStrikePrice())
                .optionType(record.getOptionType())
                .entryValue(record.getEntryValue())
                .exitValue(record.getExitValue())
                .stopLossValue(record.getStopLossValue())
                .targetValue(record.getTargetValue())
                .buyPrice(record.getBuyPrice())
                .sellPrice(record.getSellPrice())
                .tradeDate(record.getTradeDate())
                .expiryDate(record.getExpiryDate())
                .profit(summary.getProfit())
                .loss(summary.getLoss())
                .status(summary.getStatus())
                .riskRewardRatio(summary.getRiskRewardRatio())
                .capturedPoints(summary.getCapturedPoints())
                .build();
    }*/

}
