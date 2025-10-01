package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
import org.example.apifluxar.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    Optional<Batch> findByBatchCode(String batchCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM Batch b WHERE b.id = :id")
    void deleteByIdCustom(@Param("id") Long id);

    @Query("SELECT l FROM Batch l " +
            "JOIN l.product p " +
            "WHERE l.unit.id = :unitId AND p.sector.id = :sectorId")
    List<Batch> findAllBatchesInUnitAndSector(@Param("unitId") Long unitId,
                                              @Param("sectorId") Long sectorId);
}
