package com.trading.journal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeResponseList {
    @Schema(description = "List of all records", example = "{25000,CE ,....}")
    private List<TradeResponse> tradeResponseList;

    @Schema(description = "totalRecords count", example = "50")
    private long totalRecords;
}
