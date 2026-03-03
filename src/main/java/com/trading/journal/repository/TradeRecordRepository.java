package com.trading.journal.repository;

import com.trading.journal.entity.TradeRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRecordRepository extends JpaRepository<TradeRecordEntity, Long> {

    @Modifying
    @Query(value = "TRUNCATE TABLE trade_records RESTART IDENTITY", nativeQuery = true)
    void truncateAndReset();

}
