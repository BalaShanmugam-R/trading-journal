package com.trading.journal.transformer;

import com.trading.journal.entity.TradeRecordEntity;
import com.trading.journal.model.TradeRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TradeTransformer {

    public TradeRecordEntity addTrade(TradeRequest request) {
        return TradeRecordEntity.builder()
                .orderType(request.getOrderType() != null ? request.getOrderType() : "MKT")
                .quantity(request.getQuantity() != null ? request.getQuantity() : 20)
                .exchange(request.getExchange() != null ? request.getExchange() : "NSE")
                .symbol(request.getSymbol() != null ? request.getSymbol() : "NIFTY")
                .instrument(request.getInstrument() != null ? request.getInstrument() : "Options")
                .productType(request.getProductType() != null ? request.getProductType() : "Intra trade")
                .entryValue(request.getEntryValue())
                .exitValue(request.getExitValue())
                .buyPrice(request.getBuyPrice())
                .sellPrice(request.getSellPrice())
                .optionType(request.getOptionType() != null ? request.getOptionType() : "CE")
                .strikePrice(request.getStrikePrice())
                .tradeDate(request.getTradeDate() != null ? request.getTradeDate() : LocalDate.now())
                .expiryDate(request.getExpiryDate() != null ? request.getExpiryDate() : LocalDate.now().plusDays(7))
                .build();
    }

}
