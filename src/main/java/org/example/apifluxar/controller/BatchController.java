package org.example.apifluxar.controller;


import org.example.apifluxar.dto.BatchRequestDTO;
import org.example.apifluxar.dto.BatchResposeDTO;
import org.example.apifluxar.model.Batch;
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

    @GetMapping("/findById/{id}")
    public ResponseEntity<BatchResposeDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(batchService.findById(id));
    }

    @GetMapping("/findByIdLote/{loteId}")
    public ResponseEntity<BatchResposeDTO> findByIdBatch(@PathVariable String loteId){
        return ResponseEntity.ok(batchService.getBatchByIdLote(loteId));
    }

    @PostMapping("/addBatch")
    public ResponseEntity<BatchResposeDTO> addBatch(@RequestBody BatchRequestDTO batchRequestDTO){
        BatchResposeDTO batchRespose = batchService.createBatch(batchRequestDTO);
        return ResponseEntity.ok(batchRespose);
    }

    @GetMapping("/getAllBatchAndProduct")
    public ResponseEntity<List<BatchResposeDTO>> getAllLotesAndProduct(){
        List<BatchResposeDTO> bachtDTO = batchService.getAllLotesAndProduct();
        return ResponseEntity.ok(bachtDTO);
    }

    @DeleteMapping("/DeleteBatch/{id}")
    public ResponseEntity<BatchResposeDTO> deleteBatch(@PathVariable Long id){
        BatchResposeDTO batchResposeDTO = batchService.deleteBatch(id);
        return ResponseEntity.ok(batchResposeDTO);
    }

}