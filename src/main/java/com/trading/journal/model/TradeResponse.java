package com.trading.journal.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeResponse {
    public String id;
    public String description;
    public String tradeMarket;
    public String strikePrice;
    public String strikeAction;
    public String tradeAction;
    public String profit;
    public String loss;
    public Integer riskRewardRatio;
}
