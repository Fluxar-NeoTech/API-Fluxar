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

    @GetMapping("/search/bachtId/{id}")
    public ResponseEntity<List<CapacityHistoryResponseDTO>> selectBachId(@PathVariable Long id) {
        List<CapacityHistoryResponseDTO> res = capacityHistoryService.getCapacityHistoryByLote(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search/ProdutoAndSectorAndAndUnidade")
    public ResponseEntity<List<CapacityHistoryResponseDTO>> selectProdAndSectorAndUnidade(@RequestParam Long produtoId,
                                                                                          @RequestParam Long setorId,
                                                                                          @RequestParam Long unidadeId) {
        List<CapacityHistoryResponseDTO> res = capacityHistoryService.getCapacityHistoryBySectorAndProdutoAndUnidade(produtoId, setorId, unidadeId);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/bachtId/{id}")
    public ResponseEntity<Object> deleteBachId(@PathVariable Long id) {
        Integer qnt = capacityHistoryService.deleteCapacityHistoryByIdLote(id);
        return ResponseEntity.ok(qnt);
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
