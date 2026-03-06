package com.trading.journal.service;

import com.trading.journal.entity.TradeRecordEntity;
import com.trading.journal.entity.TradeSummaryEntity;
import com.trading.journal.model.TradeRequest;
import com.trading.journal.model.TradeResponse;
import com.trading.journal.model.TradeResponseList;
import com.trading.journal.repository.TradeRecordRepository;
import com.trading.journal.repository.TradeSummaryRepository;
import com.trading.journal.transformer.TradeTransformer;
import com.trading.journal.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TradeService {

    @Autowired
    TradeRecordRepository tradeRecordRepository;

    @Autowired
    TradeSummaryRepository tradeSummaryRepository;

    @Autowired
    private TradeTransformer tradeTransformer;

    public TradeResponseList getAllTrades() {
        List<TradeResponse> responseList = tradeTransformer.getAllTradeRecords();
        TradeResponseList response = TradeResponseList.builder().tradeResponseList(responseList).totalRecords(tradeRecordRepository.count())
                .build();
        return response;
    }

    public String addTrade(TradeRequest request) {

        try {
            boolean recordExists = findRecordExist(request);
            if (recordExists) {
                return Constants.RECORD_ALREADY_EXISTS;
            } else {
                TradeRecordEntity entity = tradeTransformer.addTrade(request);
                tradeRecordRepository.save(entity);

                TradeSummaryEntity summary = tradeTransformer.addTradeSummary(request);
                tradeSummaryRepository.save(summary);
                return Constants.RECORD_ADDED_SUCCESSFULLY;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean findRecordExist(TradeRequest request) {
        boolean exists = tradeRecordRepository.findByTradeKey(request.getStrikePrice(), request.getOptionType(), request.getEntryValue(), request.getExitValue());
        return exists;
    }

    public String removeTrade(TradeRequest request) {
        boolean recordExists = findRecordExist(request);
        if (recordExists) {
            tradeRecordRepository.deleteByTradeKey(request.getStrikePrice(), request.getOptionType(), request.getEntryValue(), request.getExitValue());
            return Constants.RECORD_REMOVED_SUCCESSFULLY;
        } else {
            return Constants.RECORD_NOT_AVAILABLE;
        }
    }

    @Transactional
    public String ClearAllTrade() {
        long count = tradeRecordRepository.count();
        if (count > 0) {
            tradeRecordRepository.truncateTable();
            return String.format(Constants.TOTAL_RECORDS_COUNT, count);
        } else {
            return Constants.RECORDS_EMPTY;
        }
    }
}
