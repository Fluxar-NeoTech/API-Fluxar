package org.example.apifluxar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.CapacityHistoryResponseDTO;
import org.example.apifluxar.service.CapacityHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        CapacityHistoryResponseDTO res = capacityHistoryService.getCapacityHistoryById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search/SectorAndAndUnidade")
    public ResponseEntity<List<CapacityHistoryResponseDTO>> selectProdAndSectorAndUnidade(@RequestParam Long setorId,
                                                                                          @RequestParam Long unidadeId) {
        List<CapacityHistoryResponseDTO> res = capacityHistoryService.getCapacityHistoryBySectorAndUnidade( setorId, unidadeId);
        return ResponseEntity.ok(res);
    }


    @DeleteMapping("/delete/unitId/{id}")
    public ResponseEntity<Object> deleteUnitId(@PathVariable Long id) {
        Integer qnt = capacityHistoryService.deleteCapacityHistoryByIdUnidade(id);
        return ResponseEntity.ok(qnt);
    }

    @DeleteMapping("/delete/sectorId/{id}")
    public ResponseEntity<Object> deleteSectorId(@PathVariable Long id) {
        Integer qnt = capacityHistoryService.deleteCapacityHistoryByIdSetor(id);
        return ResponseEntity.ok(qnt);
    }
}
