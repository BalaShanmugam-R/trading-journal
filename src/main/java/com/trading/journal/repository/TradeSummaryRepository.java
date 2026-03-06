package com.trading.journal.repository;

import com.trading.journal.entity.TradeSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeSummaryRepository extends JpaRepository<TradeSummaryEntity, Long> {

}
