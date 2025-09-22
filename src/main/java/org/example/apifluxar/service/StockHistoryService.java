package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.BatchResponseDTO;
import org.example.apifluxar.dto.StockHistoryResponseDTO;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.StockHistory;
import org.example.apifluxar.repository.StockHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class StockHistoryService {

    StockHistoryRepository stockHistoryRepository;
    ObjectMapper objectMapper;

    public StockHistoryService(StockHistoryRepository stockHistoryRepository, ObjectMapper objectMapper) {
        this.stockHistoryRepository = stockHistoryRepository;
        this.objectMapper = objectMapper;
    }

    public StockHistoryResponseDTO getStockHistoryById(Long id) {
            System.out.println("🔎 Buscando StockHistory com id: " + id);

            Optional<StockHistory> optional = stockHistoryRepository.findById(id);

            if (optional.isEmpty()) {
                System.out.println("⚠️ Não encontrei nenhum registro com id: " + id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado: " + id);
            }

            StockHistory stockHistory = optional.get();
            System.out.println("✅ Registro encontrado: " + stockHistory);

            StockHistoryResponseDTO dto = objectMapper.convertValue(stockHistory, StockHistoryResponseDTO.class);
            System.out.println("📦 Convertido para DTO: " + dto);

            return dto;



    }

//    public Long deleteByBatchId(String id) {
//        return stockHistoryRepository.deleteByIdLote(id);
//    }
}
