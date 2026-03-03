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

    @Column(name = "orderType")
    private String orderType;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "instrument")
    private String instrument;

    @Column(name = "productType")
    private String productType;

    @Column(name = "entryValue")
    private Double entryValue;

    @Column(name = "exitValue")
    private Double exitValue;

    @Column(name = "buyPrice")
    private Double buyPrice;

    @Column(name = "sellPrice")
    private Double sellPrice;

    @Column(name = "optionType")
    private String optionType;

    @Column(name = "strikePrice")
    private Integer strikePrice;

    @Column(name = "tradeDate")
    private LocalDate tradeDate;

    @Column(name = "expiryDate")
    private LocalDate expiryDate;
}
