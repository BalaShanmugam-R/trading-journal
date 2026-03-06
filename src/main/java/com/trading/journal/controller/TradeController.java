package com.trading.journal.controller;

import com.trading.journal.model.TradeRequest;
import com.trading.journal.model.TradeResponseList;
import com.trading.journal.model.TradeUpdateRequest;
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
    public ResponseEntity<TradeResponseList> getAllTrades() {//add filters to fetch based on date/market
        TradeResponseList response = tradeService.getAllTrades();
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

    /*@Operation(summary = "Endpoint to update editable fields in record", description = "This endpoint will be used to modify specific trade record.", operationId = "trade-journal")
    @PutMapping
    public String updateTrade(@Validated @RequestBody TradeUpdateRequest tradeRequest) {
        return tradeService.modifyTrade(tradeRequest);
    }*/

    @Operation(summary = "Endpoint to flush all trade records", description = "This endpoint will be used to delete all trade.", operationId = "trade-journal")
    @DeleteMapping
    public String ClearAllTrade() {
        return tradeService.ClearAllTrade();
    }

    @Operation(summary = "Endpoint to delete specific trade record", description = "This endpoint will be used to delete specific trade record.", operationId = "trade-journal")
    @DeleteMapping("/remove")
    public String removeTrade(@RequestBody TradeRequest request) {
        return tradeService.removeTrade(request);
    }

    //add lookup endpoint and filters and sorts slowly
    //later validation

}
