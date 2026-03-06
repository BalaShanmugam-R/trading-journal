package com.trading.journal.repository;

import com.trading.journal.entity.TradeRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TradeRecordRepository extends JpaRepository<TradeRecordEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE trade_summary, trade_records RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncateTable();

    @Query(value = """
            SELECT DISTINCT
                r.exchange, r.symbol, r.instrument, r.order_type, r.quantity, 
                r.buy_price, r.sell_price, r.strike_price, r.option_type,
                r.entry_value, r.exit_value, r.stop_loss_value, r.target_value, 
                r.trade_date, r.expiry_date,
                s.profit, s.loss,
                s.status, s.risk_reward_ratio, s.captured_points
            FROM trade_records r 
            LEFT JOIN trade_summary s ON 
                r.strike_price = s.strike_price 
                AND r.option_type = s.option_type 
                AND r.entry_value = s.entry_value 
                AND r.exit_value = s.exit_value
            ORDER BY r.trade_date DESC
            """, nativeQuery = true)
    List<Object[]> findAllTradesWithSummary();

    @Query(value = """
            SELECT EXISTS (
                SELECT 1 FROM trade_records r 
                WHERE r.strike_price = :strikePrice 
                  AND r.option_type = :optionType 
                  AND r.entry_value = :entryValue 
            )
            """, nativeQuery = true)
    boolean findExistByTradeKey(@Param("strikePrice") Integer strikePrice, @Param("optionType") String optionType, @Param("entryValue") Double entryValue);

    @Query(value = """
                SELECT * FROM trade_records r 
                WHERE r.strike_price = :strikePrice 
                  AND r.option_type = :optionType 
                  AND r.entry_value = :entryValue 
            """, nativeQuery = true)
    TradeRecordEntity findByTradeKey(@Param("strikePrice") Integer strikePrice, @Param("optionType") String optionType, @Param("entryValue") Double entryValue);

    void deleteByStrikePriceAndOptionTypeAndEntryValue(Integer strikePrice, String optionType, Double entryValue);
}
