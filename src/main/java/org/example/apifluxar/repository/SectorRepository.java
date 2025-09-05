package org.example.apifluxar.repository;

import org.example.apifluxar.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    Optional<Sector> findByNome(String nome);
}
