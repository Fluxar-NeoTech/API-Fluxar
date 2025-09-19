package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.example.apifluxar.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CapacityHistporyRepository extends JpaRepository<CapacityHistory, Long> {
    @Modifying
    @Query("SELECT c FROM CapacityHistory c WHERE c.produto.id = :idproduto and c.sector.id = :idsetor and c.unidade.id = :idunidade")
    List<CapacityHistory> findByProdutoAndSectorAndAndUnidade(@Param("idProduto") Long idProduto,
                                                              @Param("idSector") Long idSector,
                                                              @Param("idUnidade") Long idUnidade);
    @Modifying
    @Query("SELECT c FROM CapacityHistory c WHERE  c.lote.id = :idlote")
    List<CapacityHistory> findByLote(@Param("idLote") Long idLote);
    @Modifying
    @Transactional
    @Query("DELETE FROM CapacityHistory c WHERE c.lote.id = :id")
    int deleteByLoteId(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("DELETE FROM CapacityHistory c WHERE c.sector.id = :id")
    int deleteBySector(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("DELETE FROM CapacityHistory c WHERE c.unidade.id = :id")
    int deleteByUnidade(@Param("id") Long id);

}
