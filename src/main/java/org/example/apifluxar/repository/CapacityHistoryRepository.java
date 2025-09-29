package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.example.apifluxar.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CapacityHistoryRepository extends JpaRepository<CapacityHistory, Long> {
    @Query("SELECT c FROM CapacityHistory c WHERE c.sector.id = :idsector and c.unidade.id = :idunidade")
    List<CapacityHistory> findBySectorAndAndUnidade(@Param("idsector") Long idSector,
                                                              @Param("idunidade") Long idUnidade);
    @Modifying
    @Transactional
    @Query("DELETE FROM CapacityHistory c WHERE c.sector.id = :idsector and c.unidade.id = :idunidade")
    Integer deleteBySectorAndUnidade(@Param("idsector") Long id,@Param("idunidade") Long idunidade);
}
