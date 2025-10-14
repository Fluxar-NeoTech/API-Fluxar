package org.example.apifluxar.controller;

import org.example.apifluxar.dto.batch.BatchRequestDTO;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
import org.example.apifluxar.dto.batch.ProductBatchResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.openapi.BatchOpenAPI;
import org.example.apifluxar.service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/batch")
public class BatchController implements BatchOpenAPI {
    final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("search/all/product/by/unit/sector")
    public ResponseEntity<List<ProductBatchResponseDTO>> getAllBatchByUnitAndSector(@RequestParam Long unitId, @RequestParam Long sectorId) {
        List<ProductBatchResponseDTO> productBatchResponseDTO = batchService.getAllProductBatch(unitId, sectorId);
        return ResponseEntity.ok(productBatchResponseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponseDTO> addBatch(@RequestBody BatchRequestDTO batchRequestDTO){
        MessageResponseDTO batchResponse = batchService.addBatch(batchRequestDTO);
        return ResponseEntity.ok(batchResponse);
    }

    @DeleteMapping("/delete/{batchCode}")
    public ResponseEntity<MessageResponseDTO> deleteBatch(@PathVariable String batchCode){
        MessageResponseDTO batchResponse = batchService.deleteBatch(batchCode);
        return ResponseEntity.ok(batchResponse);
    }
}
