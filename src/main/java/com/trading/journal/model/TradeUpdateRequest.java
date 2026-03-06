package com.trading.journal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeUpdateRequest {

    @Schema(description = "strikePrice", example = "25000")
    private Integer strikePrice;

    @Schema(description = "optionType", example = "CE/PE")
    private String optionType;

    @Schema(description = "Trade entry value", example = "36.50")
    private Double entryValue;

    @Schema(description = "Target exit value", example = "49.50")
    private Double exitValue;      // Update P&L

    @Schema(description = "Actual Target value", example = "36.50")
    private Double targetValue;

    @Schema(description = "Stop Loss value", example = "25.00")
    private Double stopLossValue;

//    private String status;         // PROFIT/LOSS
//    private Integer quantity;      // Minor fixes
}
