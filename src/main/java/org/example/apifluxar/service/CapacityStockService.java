package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.CapacityStockRequestDTO;
import org.example.apifluxar.dto.CapacityStockResposeDTO;
import org.example.apifluxar.model.CapacityStock;
import org.example.apifluxar.repository.CapacityStockRepository;
import org.springframework.stereotype.Service;

@Service
public class CapacityStockService {
    final CapacityStockRepository capacityStockRepository;
    final ObjectMapper objectMapper;

    public CapacityStockService(CapacityStockRepository capacityStockRepository, ObjectMapper objectMapper) {
        this.capacityStockRepository = capacityStockRepository;
        this.objectMapper = objectMapper;
    }

    public CapacityStockResposeDTO addCapacityStock(CapacityStockRequestDTO capacityStockRequestDTO){
        CapacityStock capacityStock = objectMapper.convertValue(capacityStockRequestDTO, CapacityStock.class);
        capacityStockRepository.save(capacityStock);
        return objectMapper.convertValue(capacityStock, CapacityStockResposeDTO.class);
    }
}
