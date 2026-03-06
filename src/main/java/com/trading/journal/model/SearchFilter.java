package com.trading.journal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchFilter {

    @Schema(description = "Field name for the search to be applied", example = "creatorName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("fieldName")
    private String fieldName;

    @Schema(description = "search value when operator is equals or contains", example = "test123", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("fieldValue")
    private String fieldValue;

    @Schema(description = "equals or contains or between or in", example = "contains", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("operator")
    private String operator;

    @Schema(description = "from is required when operator is between", example = "2024-06-31")
    @JsonProperty("from")
    private String from;

    @Schema(description = "to is required when operator is between", example = "2024-12-31")
    @JsonProperty("to")
    private String to;

    @Schema(description = "list of values when operator is in")
    @JsonProperty("valueList")
    private List<String> valueList;
}
