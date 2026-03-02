package com.trading.journal.controller;

import com.trading.journal.model.TradeRequest;
import com.trading.journal.model.TradeResponse;
import com.trading.journal.service.TradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(name = "trade-journal-collection-api", description = "Trade Journal Collection API")
@Validated
@RestController
@RequestMapping("/api/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping
    public String getAllTrades(){
        return tradeService.getAllTrades();
    }

    @Operation(summary = "Endpoint to track trades", description = "This endpoint will be used to track trade.", operationId = "trade-journal")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(implementation = TradeResponse.class))),
            @ApiResponse (responseCode = "400", description = "Constants.HTTP_STATUS_400_DESCRIPTION", content = @Content (mediaType =MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "401", description = "Constants.HTTP_STATUS_401_DESCRIPTION" ,content = @Content (mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "403", description = "Constants.HTTP_STATUS_403_DESCRIPTION", content = @Content (mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "404", description = "Constants.HTTP_STATUS_404_DESCRIPTION", content = @Content (mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "500", description = "Constants.HTTP_STATUS_500_DESCRIPTION", content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse (responseCode = "503", description = "Constants.HTTP_STATUS_503_DESCRIPTION", content = @Content (mediaType = MediaType.APPLICATION_JSON,schema = @Schema (implementation = ErrorResponse.class)))})
    */@PostMapping
    public ResponseEntity<TradeResponse> addTrade(@RequestBody TradeRequest tradeRequest) {
        TradeResponse response= tradeService.addTrade(tradeRequest);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public String modifyTrade(@RequestBody TradeRequest tradeRequest) {
        return tradeService.modifyTrade(tradeRequest);
    }

    @DeleteMapping("/{id}")
    public String removeTrade(@PathVariable Integer id) {
        return tradeService.removeTrade(id);
    }

    //add lookup endpoint and filters and sorts slowly
    //later validation

}
