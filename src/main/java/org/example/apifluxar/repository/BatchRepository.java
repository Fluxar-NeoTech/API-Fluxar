package org.example.apifluxar.repository;

import org.example.apifluxar.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    Optional<Batch> findByIdLote(String idLote);
}