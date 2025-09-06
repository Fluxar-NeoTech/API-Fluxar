package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.CapacityStockRequestDTO;
import org.example.apifluxar.dto.CapacityStockResposeDTO;
import org.example.apifluxar.model.CapacityStock;
import org.example.apifluxar.repository.CapacityStockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<CapacityStockResposeDTO> getAllCapacityStock(){
        List<CapacityStock> capacityStock = capacityStockRepository.findAll();
        List<CapacityStockResposeDTO> responseEntityList =  new ArrayList<>();
        for (int i = 0; i < capacityStock.size(); i++) {
            responseEntityList.add(objectMapper.convertValue(capacityStock, CapacityStockResposeDTO.class));
        }
        return responseEntityList;
    }

    public CapacityStockResposeDTO findById(Long id){
        CapacityStock capacityStock = capacityStockRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(capacityStock, CapacityStockResposeDTO.class);
    }
}
