package com.trading.journal.repository;

import com.trading.journal.entity.TradeSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeSummaryRepository extends JpaRepository<TradeSummaryEntity, Long> {
    @Query(value = """
                SELECT * FROM trade_summary r 
                WHERE r.strike_price = :strikePrice 
                  AND r.option_type = :optionType 
                  AND r.entry_value = :entryValue 
            """, nativeQuery = true)
    TradeSummaryEntity findByTradeKey(@Param("strikePrice") Integer strikePrice, @Param("optionType") String optionType, @Param("entryValue") Double entryValue);
}
