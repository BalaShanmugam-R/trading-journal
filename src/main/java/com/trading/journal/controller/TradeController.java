package com.trading.journal.controller;

import com.trading.journal.model.TradeRequest;
import com.trading.journal.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping
    public String getAllTrades(){
        return tradeService.getAllTrades();
    }

    @PostMapping
    public String addTrade(@RequestBody TradeRequest tradeRequest) {
        return tradeService.addTrade(tradeRequest);
    }

    @PutMapping
    public String modifyTrade(@RequestBody TradeRequest tradeRequest){
        return tradeService.modifyTrade(tradeRequest);
    }

    @DeleteMapping("/{id}")
    public String removeTrade(@PathVariable Integer id){
        return tradeService.removeTrade(id);
    }

}
