package org.example.apifluxar.repository;

import org.example.apifluxar.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CapacityStockRepository extends JpaRepository<CapacityStock, Long> {
    @Query("SELECT c FROM CapacityStock c WHERE c.unit.id = :unitId and c.sector.id = :sectorId")
    Optional<CapacityStock> findBySectorAndUnit(@Param("unitId") Long id,
                                                   @Param("sectorId") Long idSector);

    Optional<CapacityStock> findBySectorAndUnit(Sector sector, Unit unit);
}
