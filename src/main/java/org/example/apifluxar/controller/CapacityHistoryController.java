package org.example.apifluxar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.CapacityHistoryResponseDTO;
import org.example.apifluxar.service.CapacityHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/capacityhistory")
public class CapacityHistoryController {
    final CapacityHistoryService capacityHistoryService;
    final ObjectMapper objectMapper;

    public CapacityHistoryController(CapacityHistoryService capacityHistoryService, ObjectMapper objectMapper) {
        this.capacityHistoryService = capacityHistoryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<CapacityHistoryResponseDTO> selectId(@PathVariable Long id) {
        CapacityHistoryResponseDTO dto = capacityHistoryService.getCapacityHistoryById(id);
        return ResponseEntity.ok(dto);
    }
}
