package org.example.apifluxar.controller;

import org.example.apifluxar.dto.BatchRequestDTO;
import org.example.apifluxar.dto.BatchResponseCreateDTO;
import org.example.apifluxar.dto.BatchResponseDTO;
import org.example.apifluxar.service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/batch")
public class BatchController {
    final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/search/id/{loteId}")
    public ResponseEntity<BatchResponseDTO> findByIdBatch(@PathVariable String loteId) {
        return ResponseEntity.ok(batchService.getBatchByIdLote(loteId));
    }

    @GetMapping("/search/batch")
    public ResponseEntity<List<BatchResponseDTO>> findAllBatch() {
        return ResponseEntity.ok(batchService.getAllBatch());
    }

    @PostMapping("/add/batch")
    public ResponseEntity<BatchResponseCreateDTO> addBatch(@RequestBody BatchRequestDTO batchRequestDTO){
        BatchResponseCreateDTO batchRespose = batchService.createBatch(batchRequestDTO);
        return ResponseEntity.ok(batchRespose);
    }

//    @GetMapping("/getAllBatchAndProduct")
//    public ResponseEntity<List<BatchResponseDTO>> getAllLotesAndProduct(){
//        List<BatchResponseDTO> bachtDTO = batchService.getAllLotesAndProduct();
//        return ResponseEntity.ok(bachtDTO);
//    }

//    @DeleteMapping("/DeleteBatch/{id}")
//    public ResponseEntity<BatchResponseDTO> deleteBatch(@PathVariable String id){
//        BatchResponseDTO batchResposeDTO = batchService.deleteBatch(id);
//        return ResponseEntity.ok(batchResposeDTO);
//    }
}
