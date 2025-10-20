package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.example.apifluxar.model.*;
import org.example.apifluxar.projection.CapacityHistoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CapacityHistoryRepository extends JpaRepository<CapacityHistory, Long> {
    @Query("SELECT c FROM CapacityHistory c WHERE c.unit.id = :unitId")
    List<CapacityHistory> findByUnit(@Param("unitId") Long unitId);

    @Query(value = "SELECT porcent_ocupacao_setor as OccupancyPercentage, volume_restante_setor RemainingVolume " +
            " FROM obter_ocupacao_setor(:sectorId,:employeeId)", nativeQuery = true)
    CapacityHistoryProjection getSectorCapacityUsage(@Param("sectorId") Long sectorId,
                                                      @Param("employeeId") Long employeeId);
}
