package com.trading.journal.service;

import com.trading.journal.entity.TradeRecordEntity;
import com.trading.journal.entity.TradeSummaryEntity;
import com.trading.journal.model.TradeRequest;
import com.trading.journal.model.TradeResponse;
import com.trading.journal.model.TradeResponseList;
import com.trading.journal.repository.TradeRecordRepository;
import com.trading.journal.repository.TradeSummaryRepository;
import com.trading.journal.transformer.TradeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {

    @Autowired
    TradeRecordRepository tradeRecordRepository;

    @Autowired
    TradeSummaryRepository tradeSummaryRepository;

    @Autowired
    private TradeTransformer tradeTransformer;

    public TradeResponseList getAllTrades() {
        //fetch all records and map
        List<TradeResponse> responseList = getAllTradeRecords();
        TradeResponseList response = TradeResponseList.builder().tradeResponseList(responseList).totalRecords(tradeRecordRepository.count())
                .build();
        return response;
    }

    private List<TradeResponse> getAllTradeRecords() {
        return tradeRecordRepository.findAllTradesWithSummary().stream()
                .map(row -> TradeResponse.builder()
                        .exchange((String) row[0])
                        .symbol((String) row[1])
                        .instrument((String) row[2])
                        .orderType((String) row[3])
                        .quantity(((Number) row[4]).intValue())
                        .buyPrice(((Number) row[5]).doubleValue())
                        .sellPrice(((Number) row[6]).doubleValue())
                        .strikePrice(((Number) row[7]).intValue())
                        .optionType((String) row[8])
                        .entryValue(((Number) row[9]).doubleValue())
                        .exitValue(((Number) row[10]).doubleValue())
                        .stopLossValue(((Number) row[11]).doubleValue())
                        .targetValue(((Number) row[12]).doubleValue())
                        .tradeDate((LocalDate) row[13])
                        .expiryDate((LocalDate) row[14])
                        .profit(((Number) row[15]).doubleValue())
                        .loss(((Number) row[16]).doubleValue())
                        .status((String) row[17])
                        .riskRewardRatio((String) row[18])
                        .capturedPoints(((Number) row[19]).intValue())
                        .build()
                )
                .collect(Collectors.toList());
    }


    public String addTrade(TradeRequest request) {

        try {
            boolean recordExists = findRecordExist(request);
            if (recordExists) {
                return "record already exists";
            } else {
                TradeRecordEntity entity = tradeTransformer.addTrade(request);
                tradeRecordRepository.save(entity);

                TradeSummaryEntity summary = tradeTransformer.addTradeSummary(request);
                tradeSummaryRepository.save(summary);
                return "added trade successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean findRecordExist(TradeRequest request) {
        boolean exists = tradeRecordRepository.findByTradeKey(request.getStrikePrice(), request.getOptionType(), request.getEntryValue(), request.getExitValue());
        return exists;
    }

    public String modifyTrade(TradeRequest tradeRequest) {
        //modify trade details
        return "modified trade successfully";
    }

    public String removeTrade(Integer id) {
        //remove trade details
        return "removed";
    }

    @Transactional
    public String ClearAllTrade() {
        long count = tradeRecordRepository.count();
        if (count > 0) {
            tradeRecordRepository.truncateTable();
            return String.format("Total :  %d records Cleared", count);
        } else {
            return "No records to clear";
        }
    }
}
