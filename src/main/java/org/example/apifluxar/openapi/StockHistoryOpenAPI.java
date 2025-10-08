package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.stockHistory.StockHistoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StockHistoryOpenAPI {
    @Operation(summary = "Pesquisa o histórico de estoque por ID da unidade e ID do setor",
            description = "Retorna uma lista de históricos de estoque para a unidade e setor fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico de estoque encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Histórico de estoque não encontrado para a unidade e setor fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<StockHistoryResponseDTO> getStockHistoryById(@RequestParam Long unitId, @RequestParam Long sectorId);
}
