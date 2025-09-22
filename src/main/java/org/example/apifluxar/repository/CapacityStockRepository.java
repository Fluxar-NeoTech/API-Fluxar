package org.example.apifluxar.repository;

import org.example.apifluxar.model.CapacityStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CapacityStockRepository extends JpaRepository<CapacityStock, Long> {

}
