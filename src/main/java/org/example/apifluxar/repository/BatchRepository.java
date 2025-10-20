package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.example.apifluxar.dto.batch.BatchRequestDTO;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    Optional<Batch> findByBatchCode(String batchCode);

    @Query("SELECT l FROM Batch l " +
            "JOIN l.product p " +
            "WHERE l.unit.id = :unitId AND p.sector.id = :sectorId")
    List<Batch> findAllBatchesInUnitAndSector(@Param("unitId") Long unitId,
                                              @Param("sectorId") Long sectorId);


    @Transactional
    @Modifying
    @Query(value = """
    CALL adicionar_lote(
        :sku_param,
        CAST(:val AS timestamp),
        CAST(:alt AS numeric),
        CAST(:comp AS numeric),
        CAST(:larg AS numeric),
        :nome_prod,
        :tipo_prod,
        :id_unidade_param,
        :id_setor
    )
""", nativeQuery = true)
    void addBatch(
            @Param("sku_param") String sku_param,
            @Param("val") java.sql.Timestamp val,
            @Param("alt") Double alt,
            @Param("comp") Double comp,
            @Param("larg") Double larg,
            @Param("nome_prod") String nome_prod,
            @Param("tipo_prod") String tipo_prod,
            @Param("id_unidade_param") Long id_unidade_param,
            @Param("id_setor") Long id_setor
    );


    @Transactional
    @Modifying
    @Query(value = "CALL remover_lote(:sku_param)", nativeQuery = true)
    void deleteBatch(@Param("sku_param") String sku_param);
}
