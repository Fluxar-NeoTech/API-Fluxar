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

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Batch b WHERE b.id = :id")
//    void deleteByIdCustom(@Param("id") Long id);

    @Query("SELECT l FROM Batch l " +
            "JOIN l.product p " +
            "WHERE l.unit.id = :unitId AND p.sector.id = :sectorId")
    List<Batch> findAllBatchesInUnitAndSector(@Param("unitId") Long unitId,
                                              @Param("sectorId") Long sectorId);


    @Procedure(procedureName = "adicionar_lote")
    void addBatch(
            @Param("sku_param") String sku_param,
            @Param("val") java.sql.Date val,
            @Param("alt") Double alt,
            @Param("comp") Double comp,
            @Param("larg") Double larg,
            @Param("nome_prod") String nome_prod,
            @Param("tipo_prod") String tipo_prod,
            @Param("id_unidade_param") Long id_unidade_param,
            @Param("id_setor") Long id_setor
    );

    @Procedure(procedureName = "remover_do_estoque")
    void deleteBatch(
            @Param("nome_prod") String nomeProd,
            @Param("sku_param") String skuParam
    );

}
