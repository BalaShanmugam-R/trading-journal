package com.trading.journal.service;

import com.trading.journal.model.TradeRequest;
import com.trading.journal.model.TradeResponse;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    public String getAllTrades() {
        //fetch all records
        return "fetched all details";
    }

    public TradeResponse addTrade(TradeRequest tradeRequest) {
        //transform to requirements and add all details to DB
        return TradeResponse.builder().tradeAction("PE").strikeAction("25000").tradeMarket("Nifty").build();
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
