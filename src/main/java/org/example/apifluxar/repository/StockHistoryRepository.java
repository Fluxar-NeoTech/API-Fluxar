package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.apache.juli.logging.Log;
import org.example.apifluxar.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
//    Long deleteByIdLote(String loteId);
    @Modifying
    @Transactional
    @Query("DELETE FROM StockHistory sh WHERE sh.lote.id = :loteId")
    int deleteAllByLoteId(@Param("loteId") Long loteId);

    @Query("SELECT sh FROM StockHistory sh WHERE sh.unidade.id = :unidadeId and sh.setor.id = :setorId")
    Optional<StockHistory> findByUnidadeIdAndSetorId(@Param("unidadeId") Long unidadeId, @Param("setorId") Long setorId);


}
