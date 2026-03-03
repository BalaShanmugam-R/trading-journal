package com.trading.journal.controller;

import com.trading.journal.entity.TradeRecordEntity;
import com.trading.journal.model.TradeRequest;
import com.trading.journal.service.TradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "trade-journal-collection-api", description = "Trade Journal Collection API")
@Validated
@RestController
@RequestMapping("/api/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Operation(summary = "Endpoint to fetch all trades", description = "This endpoint will be used to fetch all trade.", operationId = "trade-journal")
    @GetMapping
    public ResponseEntity<List<TradeRecordEntity>> getAllTrades() {//add filters to fetch based on date/market
        List<TradeRecordEntity> response = tradeService.getAllTrades();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Endpoint to add trades", description = "This endpoint will be used to add new trade.", operationId = "trade-journal")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(implementation = TradeResponse.class))),
            @ApiResponse (responseCode = "400", description = "Constants.HTTP_STATUS_400_DESCRIPTION", content = @Content (mediaType =MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "401", description = "Constants.HTTP_STATUS_401_DESCRIPTION" ,content = @Content (mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "403", description = "Constants.HTTP_STATUS_403_DESCRIPTION", content = @Content (mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "404", description = "Constants.HTTP_STATUS_404_DESCRIPTION", content = @Content (mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "500", description = "Constants.HTTP_STATUS_500_DESCRIPTION", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "503", description = "Constants.HTTP_STATUS_503_DESCRIPTION", content = @Content (mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class)))})
    */
    @PostMapping
    public ResponseEntity<String> addTrade(@RequestBody TradeRequest tradeRequest) {
        String response = tradeService.addTrade(tradeRequest);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Endpoint to modify specific trade record", description = "This endpoint will be used to modify specific trade record.", operationId = "trade-journal")
    @PutMapping
    public String modifyTrade(@RequestBody TradeRequest tradeRequest) {
        return tradeService.modifyTrade(tradeRequest);
    }

    @Operation(summary = "Endpoint to delete all trades", description = "This endpoint will be used to delete all trade.", operationId = "trade-journal")
    @DeleteMapping
    public String ClearAllTrade() {
        return tradeService.ClearAllTrade();
    }

    @Operation(summary = "Endpoint to delete specific trade record", description = "This endpoint will be used to delete specific trade record.", operationId = "trade-journal")
    @DeleteMapping("/{id}")
    public String removeTrade(@PathVariable Integer id) {
        return tradeService.removeTrade(id);
    }

    //add lookup endpoint and filters and sorts slowly
    //later validation

}
