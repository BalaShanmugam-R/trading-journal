package com.trading.journal.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "trade_summary")
public class TradeSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_id")
    private Long summaryId;

    @Column(name = "strike_price")
    private Integer strikePrice;

    @Column(name = "option_type")
    private String optionType;

    @Column(name = "entry_value")
    private Double entryValue;

    @Column(name = "exit_value")
    private Double exitValue;

    @Column(name = "target_value")
    private Double targetValue;

    @Column(name = "stop_loss_value")
    private Double stopLossValue;

    @Column(name = "buy_price")
    private Double buyPrice;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "profit")
    private Double profit;

    @Column(name = "loss")
    private Double loss;

    @Column(name = "risk_reward_ratio")
    private String riskRewardRatio;

    @Column(name = "captured_points")
    private Integer capturedPoints;

    public String getTradeKey(){
        return  strikePrice + "_" + optionType + "_" + String.valueOf(entryValue) + "_" + String.valueOf(exitValue);
    }
}

