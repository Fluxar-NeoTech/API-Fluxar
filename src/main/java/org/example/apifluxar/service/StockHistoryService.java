package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.stockHistory.StockHistoryResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.model.StockHistory;
import org.example.apifluxar.repository.StockHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class StockHistoryService {

    StockHistoryRepository stockHistoryRepository;
    ObjectMapper objectMapper;

    public StockHistoryService(StockHistoryRepository stockHistoryRepository, ObjectMapper objectMapper) {
        this.stockHistoryRepository = stockHistoryRepository;
        this.objectMapper = objectMapper;
    }

    public StockHistoryResponseDTO getStockHistoryById(Long unitId, Long sectorId) {
        StockHistory stockHistory = stockHistoryRepository.findByUnidadeIdAndSetorId(unitId, sectorId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade ou setor não encontrado"));

        StockHistoryResponseDTO dto = objectMapper.convertValue(stockHistory, StockHistoryResponseDTO.class);
        return dto;

    }



    public void deleteByBatchId(Long id) {
        int deletedCount = stockHistoryRepository.deleteAllByLoteId(id);
    }

}
