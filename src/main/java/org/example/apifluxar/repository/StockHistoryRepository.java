package org.example.apifluxar.repository;

import org.example.apifluxar.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    Long deleteByIdLote(String loteId);
}
