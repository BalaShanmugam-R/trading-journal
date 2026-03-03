package com.trading.journal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeRequest {

    @Schema(description = "Id", example = "1")
    public Integer id;

    @Schema(description = "orderType", example = "MKT/AMO")
    private String orderType;

    @Schema(description = "quantity", example = "20")
    private Integer quantity;

    @Schema(description = "exchange", example = "NSE")
    private String exchange;

    @Schema(description = "symbol", example = "NIFTY/SENSEX")
    private String symbol;

    @Schema(description = "instrument", example = "Options/Commodity")
    private String instrument;

    @Schema(description = "productType", example = "Intra trade/Normal")
    private String productType;

    @Schema(description = "Trade entry value", example = "36.50")
    private Double entryValue;

    @Schema(description = "Target value", example = "49.50")
    private Double exitValue;

    @Schema(description = "Buy price", example = "2,500.05")
    private Double buyPrice;

    @Schema(description = "Sell price", example = "3,150.50")
    private Double sellPrice;

    @Schema(description = "optionType", example = "CE/PE")
    private String optionType;

    @Schema(description = "strikePrice", example = "25000")
    private Integer strikePrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "trade date in DD/MM/YYYY format", example = "03/03/2026")
    private LocalDate tradeDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "expiry date in DD/MM/YYYY format", example = "12/12/2025")
    private LocalDate expiryDate;

}
