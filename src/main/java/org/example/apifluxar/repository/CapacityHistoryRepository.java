package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.example.apifluxar.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CapacityHistoryRepository extends JpaRepository<CapacityHistory, Long> {
    @Query("SELECT c FROM CapacityHistory c WHERE c.sector.id = :sectorId and c.unit.id = :unitId")
    List<CapacityHistory> findBySectorAndAndUnit(@Param("sectorId") Long sectorId,
                                                              @Param("unitId") Long unitId);
    @Modifying
    @Transactional
    @Query("DELETE FROM CapacityHistory c WHERE c.sector.id = :sectorId and c.unit.id = :unitId")
    Integer deleteBySectorAndUnit(@Param("sectorId") Long sectorId, @Param("unitId") Long unitId);
}
