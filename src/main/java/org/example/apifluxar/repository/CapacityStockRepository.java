package org.example.apifluxar.repository;

import org.example.apifluxar.model.CapacityStock;
import org.example.apifluxar.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CapacityStockRepository extends JpaRepository<CapacityStock, Long> {
    @Query("SELECT c FROM CapacityStock c WHERE c.unidade.id = :id")
    Optional<CapacityStock> findByUnidade(@Param("id") Long id);
}
