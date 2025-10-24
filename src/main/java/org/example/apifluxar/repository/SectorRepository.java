package org.example.apifluxar.repository;

import org.example.apifluxar.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    @Query("SELECT (cs.maxCapacity - coalesce(SUM(b.volume), 0)) as volume_restante_setor " +
            "FROM Sector s\n" +
            "JOIN Employee e ON e.sector.id = s.id\n" +
            "left JOIN CapacityStock cs ON cs.sector.id = s.id\n" +
            " left JOIN Product p ON p.sector.id = s.id\n" +
            " left JOIN Batch b ON b.product.id = p.id\n" +
            "WHERE s.id = :sectorId AND e.id = :employeeId\n" +
            "GROUP BY cs.maxCapacity")
    Optional<Double> getRemainingVolumeInSector(@Param("sectorId") Long sectorId,
                                                @Param("employeeId") Long employeeId);


    @Query("SELECT coalesce(SUM(b.volume), 0) as volume_utilizado_setor FROM Batch b\n" +
            "JOIN Product p ON p.id = b.product.id\n" +
            "JOIN Sector s ON s.id = p.sector.id\n" +
            "JOIN Employee e ON e.sector.id = s.id\n" +
            "WHERE s.id = :sectorId AND e.id = :employeeId")
    Optional<Double> getUsedVolumeInSector(@Param("sectorId") Long sectorId,
                                           @Param("employeeId") Long employeeId);
}
