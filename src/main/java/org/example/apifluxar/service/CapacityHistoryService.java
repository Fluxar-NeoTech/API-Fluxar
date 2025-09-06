package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.CapacityHistoryResponseDTO;
import org.example.apifluxar.model.CapacityHistory;
import org.example.apifluxar.repository.CapacityHistporyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CapacityHistoryService {
    final CapacityHistporyRepository capacityHistporyRepository;
    final ObjectMapper objectMapper;

    public CapacityHistoryService(CapacityHistporyRepository capacityHistoryRepository, ObjectMapper objectMapper) {
        this.capacityHistporyRepository = capacityHistoryRepository;
        this.objectMapper = objectMapper;
    }

    public CapacityHistoryResponseDTO getCapacityHistoryById(Long id) {
        CapacityHistory capacityHistory = capacityHistporyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(capacityHistory, CapacityHistoryResponseDTO.class);
    }
}
