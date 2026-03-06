package com.trading.journal.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeResponse {
    // IDENTIFICATION
    private String exchange;
    private String symbol;
    private String instrument;
    private String orderType;
    private Integer quantity;

    // OPTION DETAILS
    private Integer strikePrice;
    private String optionType;

    // PRICES (Planned vs Actual)
    private Double entryValue;      // Actual entry point
    private Double exitValue;       // Actual exit point
    private Double stopLossValue;   // Planned SL
    private Double targetValue;     // Planned target
    private Double buyPrice;        // Actual entry price in rupees
    private Double sellPrice;       // Actual exit price in rupees

    // TIMING
    private LocalDate tradeDate;
    private LocalDate expiryDate;

    // RESULTS
    private String status;          // COMPLETED/PARTIAL/STOPPED
    private Double profit;
    private Double loss;
    private String riskRewardRatio; // "1:2"
    private Integer capturedPoints;  // +50 / -25

    /*// METADATA
    private String tradeKey;
    private Long recordId;
    private Long summaryId;*/
}
