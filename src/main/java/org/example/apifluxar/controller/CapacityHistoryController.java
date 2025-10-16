package org.example.apifluxar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryOccupationResponse;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.openapi.CapacityHistoryOpenAPI;
import org.example.apifluxar.service.CapacityHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/capacityHistory")
public class CapacityHistoryController implements CapacityHistoryOpenAPI {
    final CapacityHistoryService capacityHistoryService;
    final ObjectMapper objectMapper;

    public CapacityHistoryController(CapacityHistoryService capacityHistoryService, ObjectMapper objectMapper) {
        this.capacityHistoryService = capacityHistoryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/search/by/unit/{unitId}")
    public ResponseEntity<CapacityHistoryResponseDTO> getCapacityHistoryByUnit(@PathVariable Long unitId) {
        CapacityHistoryResponseDTO res = capacityHistoryService.getCapacityHistoryByUnit(unitId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search/occupation/by/sector")
    public ResponseEntity<CapacityHistoryOccupationResponse> getSectorCapacityUsage(@RequestParam Long sectorId, @RequestParam Long employeeId) {
        return ResponseEntity.ok(capacityHistoryService.getSectorCapacityUsage(sectorId, employeeId));
    }
}