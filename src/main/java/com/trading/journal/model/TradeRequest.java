package com.trading.journal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeRequest {

    @Schema (description = "Id", example = "1")
    public Integer id;

    public String description;
    public String tradeMarket;
    public String strikePrice;
    public String strikeAction;
    public String tradeAction;
    public Integer entry;
    public Integer exit;
    public Integer result;

    private String transactionType;
    private String orderType;
    private String quantity;
    private String exchange;
    private String symbol;
    private String instrument;
    private String productType;
    private String sort_order;
    private String price;
    private String option_type;
    private String strike_price;
    private String expiray_date;


   /* "transactionType":"B",
            "orderType":"MKT",
            "quantity":"1",
            "exchange":"NSE",
            "symbol":"NIFTY",
            "instrument":"OPT",
            "productType":"I",
            "sort_order":"1",
            "price":"0",
            "option_type":"CE",
            "strike_price":"25150.0",
            "expiry_date":"2026-03-02"*/
}
