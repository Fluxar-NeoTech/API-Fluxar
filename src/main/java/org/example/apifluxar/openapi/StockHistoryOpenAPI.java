package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.stockHistory.StockHistoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface StockHistoryOpenAPI {
    @Operation(summary = "Buscar histórico de estoque por ID",
            description = "Retorna os detalhes de um histórico de estoque específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Histórico de estoque encontrado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Histórico de estoque não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<StockHistoryResponseDTO> getStockHistoryById(@RequestParam Long unitId, @RequestParam Long sectorId);
}
