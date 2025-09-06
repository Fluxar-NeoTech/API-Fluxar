package org.example.apifluxar.repository;

import org.example.apifluxar.model.CapacityStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityStockRepository extends JpaRepository<CapacityStock, Long> {
}
