package com.trading.journal.service;

import com.trading.journal.entity.TradeRecordEntity;
import com.trading.journal.model.TradeRequest;
import com.trading.journal.repository.TradeRecordRepository;
import com.trading.journal.transformer.TradeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    TradeRecordRepository tradeRecordRepository;

    @Autowired
    private TradeTransformer tradeTransformer;

    public List<TradeRecordEntity> getAllTrades() {
        //fetch all records
        List<TradeRecordEntity> response = tradeRecordRepository.findAll();
        return response;
    }

    public String addTrade(TradeRequest request) {
        TradeRecordEntity entity = tradeTransformer.addTrade(request);
        tradeRecordRepository.save(entity);
        return "added trade successfully";
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
            tradeRecordRepository.truncateAndReset();
            return String.format("Total :  %d records Cleared", count);
        } else {
            return "No records to clear";
        }
    }
}
