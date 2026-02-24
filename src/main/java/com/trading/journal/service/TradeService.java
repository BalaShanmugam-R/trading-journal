package com.trading.journal.service;

import com.trading.journal.model.TradeRequest;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    public String getAllTrades() {
        //fetch all records
        return "fetched all details";
    }

    public String addTrade(TradeRequest tradeRequest) {
        //transform to requirements and add all details to DB
        return "Trade Details Added Successfully";
    }

    public String modifyTrade(TradeRequest tradeRequest) {
        //modify trade details
        return "modified trade successfully";
    }

    public String removeTrade(Integer id) {
        //remove trade details
        return "removed";
    }

}
