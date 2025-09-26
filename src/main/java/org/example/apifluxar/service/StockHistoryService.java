package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.stockHistory.StockHistoryResponseDTO;
import org.example.apifluxar.model.StockHistory;
import org.example.apifluxar.repository.StockHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StockHistoryService {

    StockHistoryRepository stockHistoryRepository;
    ObjectMapper objectMapper;

    public StockHistoryService(StockHistoryRepository stockHistoryRepository, ObjectMapper objectMapper) {
        this.stockHistoryRepository = stockHistoryRepository;
        this.objectMapper = objectMapper;
    }

    public StockHistoryResponseDTO getStockHistoryById(Long id) {
        StockHistory stockHistory = stockHistoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(stockHistory, StockHistoryResponseDTO.class);
    }



    public void deleteByBatchId(Long id) {
        int deletedCount = stockHistoryRepository.deleteAllByLoteId(id);
    }

}
