package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.example.apifluxar.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    Optional<Batch> findByIdLote(String idLote);

    @Modifying
    @Transactional
    @Query("DELETE FROM Batch b WHERE b.id = :id")
    void deleteByIdCustom(@Param("id") Long id);

}
