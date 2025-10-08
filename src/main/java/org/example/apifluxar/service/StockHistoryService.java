package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.stockHistory.StockHistoryResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.model.StockHistory;
import org.example.apifluxar.repository.StockHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockHistoryService {

    StockHistoryRepository stockHistoryRepository;

    public StockHistoryService(StockHistoryRepository stockHistoryRepository, ObjectMapper objectMapper) {
        this.stockHistoryRepository = stockHistoryRepository;
    }

    public StockHistoryResponseDTO getStockHistoryById(Long unitId, Long sectorId) {
        List<StockHistory> stockHistory = stockHistoryRepository.findByUnitAndSector(unitId, sectorId);
        List<StockHistoryResponseDTO> dto = new ArrayList<>();

        if (stockHistory.isEmpty()) {
            throw new EntityNotFoundException("Histórico de estoque não encontrado para a unidade e setor fornecidos.");
        }

        for (StockHistory sh : stockHistory) {
            dto.add(new StockHistoryResponseDTO(sh.getMovement(), sh.getVolumeHandled(), sh.getDate()));
        }

        return dto.getLast();
    }
}
