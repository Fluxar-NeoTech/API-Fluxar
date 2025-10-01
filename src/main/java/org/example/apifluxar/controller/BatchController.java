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
public class BatchController {
    final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

//    @GetMapping("/search/by/code/{batchCode}")
//    public ResponseEntity<BatchResponseDTO> getByBatchCode(@PathVariable String batchCode) {
//        return ResponseEntity.ok(batchService.getBatchByCode(batchCode));
//    }

//    @GetMapping("/search/by/unit/{unitId}")
//    @Operation(summary = "Listar todos os lotes por ID da unidade",
//            description = "Retorna uma lista de todos os lotes cadastrados no sistema filtrando pelo ID da unidade.")
//    @ApiResponses({
//            @ApiResponse( responseCode = "200", description = "Lista de lotes retornada com sucesso"),
//            @ApiResponse( responseCode = "404", description = "Unidade não encontrada"),
//            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
//    })
//    public ResponseEntity<List<BatchResponseDTO>> getAllBatchByUnit(@PathVariable Long unitId) {
//        return ResponseEntity.ok(batchService.getAllBatchByUnit(unitId));
//    }

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

//    @GetMapping("/getAllBatchAndProduct")
//    public ResponseEntity<List<BatchResponseDTO>> getAllLotesAndProduct(){
//        List<BatchResponseDTO> bachtDTO = batchService.getAllLotesAndProduct();
//        return ResponseEntity.ok(bachtDTO);
//    }

    @DeleteMapping("/delete/{batchCode}")
    public ResponseEntity<MessageResponseDTO> deleteBatch(@PathVariable String batchCode){
        MessageResponseDTO batchResponseDTO = batchService.deleteBatch(batchCode);
        return ResponseEntity.ok(batchResponseDTO);
    }
}
