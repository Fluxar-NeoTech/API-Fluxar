package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.batch.BatchRequestDTO;
import org.example.apifluxar.dto.batch.BatchResponseCreateDTO;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
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

    @GetMapping("/search/by/code/{batchCode}")
    @Operation(summary = "Buscar lote por ID",
            description = "Retorna os detalhes de um lote específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lote encontrado com sucesso")
            ,@ApiResponse( responseCode = "404", description = "Lote não encontrado")
            ,@ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<BatchResponseDTO> getByBatchCode(@PathVariable String batchCode) {
        return ResponseEntity.ok(batchService.getBatchByCode(batchCode));
    }

    @GetMapping("/search/by/unit/{unitId}")
    @Operation(summary = "Listar todos os lotes por ID da unidade",
            description = "Retorna uma lista de todos os lotes cadastrados no sistema filtrando pelo ID da unidade.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lista de lotes retornada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Unidade não encontrada"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<BatchResponseDTO>> getAllBatchByUnit(@PathVariable Long unitId) {
        return ResponseEntity.ok(batchService.getAllBatchByUnit(unitId));
    }

    @PostMapping("/add")
    @Operation(summary = "Adicionar um novo lote",
            description = "Adiciona um novo lote ao sistema com base nos dados fornecidos.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lote adicionado com sucesso"),
            @ApiResponse( responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<BatchResponseCreateDTO> addBatch(@RequestBody BatchRequestDTO batchRequestDTO){
        BatchResponseCreateDTO batchRespose = batchService.addBatch(batchRequestDTO);
        return ResponseEntity.ok(batchRespose);
    }

//    @GetMapping("/getAllBatchAndProduct")
//    public ResponseEntity<List<BatchResponseDTO>> getAllLotesAndProduct(){
//        List<BatchResponseDTO> bachtDTO = batchService.getAllLotesAndProduct();
//        return ResponseEntity.ok(bachtDTO);
//    }

    @DeleteMapping("/delete/{batchCode}")
    @Operation(summary = "Deletar um lote por ID", description = "Deleta um lote do sistema com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lote deletado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<BatchResponseDTO> deleteBatch(@PathVariable String batchCode){
        BatchResponseDTO batchResposeDTO = batchService.deleteBatch(batchCode);
        return ResponseEntity.ok(batchResposeDTO);
    }
}
