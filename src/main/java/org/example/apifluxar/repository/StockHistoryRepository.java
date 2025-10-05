package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.apache.juli.logging.Log;
import org.example.apifluxar.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
//    Long deleteByIdLote(String loteId);
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM StockHistory sh WHERE sh.batch.id = :batchCode")
//    Integer deleteAllByBatchCode(@Param("batchCode") Long batchCode);

    @Query("SELECT sh FROM StockHistory sh WHERE sh.unit.id = :unitId and sh.sector.id = :sectorId")
    Optional<StockHistory> findByUnitAndSector(@Param("unitId") Long unitId, @Param("sectorId") Long sectorId);


}
