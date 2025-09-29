package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.stockHistory.StockHistoryResponseDTO;
import org.example.apifluxar.service.StockHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/stockhistory")
public class StockHistoryController {
    final StockHistoryService stockHistoryService;

    public StockHistoryController(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping("search/id/{id}")
    @Operation(summary = "Buscar histórico de estoque por ID",
            description = "Retorna os detalhes de um histórico de estoque específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Histórico de estoque encontrado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Histórico de estoque não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<StockHistoryResponseDTO> selectId(@PathVariable Long unitId, @PathVariable Long sectorId) {
        StockHistoryResponseDTO dto = stockHistoryService.getStockHistoryById(unitId, sectorId);
        return ResponseEntity.ok(dto);
    }
}
