package com.trading.journal.service;

import com.trading.journal.entity.TradeRecordEntity;
import com.trading.journal.entity.TradeSummaryEntity;
import com.trading.journal.model.TradeRequest;
import com.trading.journal.model.TradeResponse;
import com.trading.journal.model.TradeResponseList;
import com.trading.journal.model.TradeUpdateRequest;
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
        List<TradeResponse> responseList = tradeTransformer.getAllTradeRecords();
        TradeResponseList response = TradeResponseList.builder().tradeResponseList(responseList).totalRecords(tradeRecordRepository.count())
                .build();
        return response;
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

    /*public String modifyTrade(TradeUpdateRequest request) {
        //modify trade details
        try {
            boolean recordExists = findRecordExist(request);
            if (recordExists) {
                tradeRecordRepository.deleteByTradeKey(request.getStrikePrice(), request.getOptionType(), request.getEntryValue(), request.getExitValue());
                return "Record modified successfully";
            } else {
                TradeRecordEntity entity = tradeTransformer.addTrade(request);
                tradeRecordRepository.save(entity);

                TradeSummaryEntity summary = tradeTransformer.addTradeSummary(request);
                tradeSummaryRepository.save(summary);

                return "modified trade successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    public String removeTrade(TradeRequest request) {
        //remove  specific trade details
        boolean recordExists = findRecordExist(request);
        if (recordExists) {
            tradeRecordRepository.deleteByTradeKey(request.getStrikePrice(), request.getOptionType(), request.getEntryValue(), request.getExitValue());
            return "Record deleted successfully";
        } else {
            return "Record doesn't exist";
        }
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
