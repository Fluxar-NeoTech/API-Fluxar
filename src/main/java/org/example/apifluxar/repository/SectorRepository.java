package org.example.apifluxar.repository;

import org.example.apifluxar.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    @Query("SELECT (cs.maxCapacity - SUM(b.volume)) as volume_restante_setor FROM Batch b\n" +
            "JOIN Product p ON p.id = b.product.id\n" +
            "JOIN Sector s ON s.id = p.sector.id\n" +
            "JOIN CapacityStock cs ON cs.sector.id = s.id\n" +
            "WHERE s.id = :sectorId\n" +
            "GROUP BY cs.maxCapacity\n")
    Optional<Double> getRemainingVolumeInSector(@Param("sectorId") Long sectorId);

    @Query("SELECT SUM(b.volume) as volume_utilizado_setor FROM Batch b\n" +
            "JOIN Product p ON p.id = b.product.id\n" +
            "JOIN Sector s ON s.id = p.sector.id\n" +
            "WHERE s.id = :sectorId")
    Optional<Double> getUsedVolumeInSector(@Param("sectorId") Long sectorId);
}
