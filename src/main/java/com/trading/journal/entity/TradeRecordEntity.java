package com.trading.journal.entity;

import com.trading.journal.model.TradeRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "trade_records")
public class TradeRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_type")
    private String orderType;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "instrument")
    private String instrument;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "strike_price")
    private Integer strikePrice;

    @Column(name = "option_type")
    private String optionType;

    @Column(name = "quantity")
    private Integer quantity;

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

    @Column(name = "sellPrice")
    private Double sellPrice;

    @Column(name = "tradeDate")
    private LocalDate tradeDate;

    @Column(name = "expiryDate")
    private LocalDate expiryDate;

    public String getTradeKey(){
        return  strikePrice + "_" + optionType + "_" + String.valueOf(entryValue) + "_" + String.valueOf(exitValue);
    }

}
