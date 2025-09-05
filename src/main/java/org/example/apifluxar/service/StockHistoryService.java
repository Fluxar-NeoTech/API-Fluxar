package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.StockHistoryResponseDTO;
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

    public StockHistoryResponseDTO getStockHistoryById(Long id) {
        StockHistory stockHistory = stockHistoryRepository.findById(id).orElse(null);
        return objectMapper.convertValue(stockHistory, StockHistoryResponseDTO.class);
    }
}
