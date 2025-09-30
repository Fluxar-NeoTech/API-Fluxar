package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.capacityStock.CapacityStockRequestDTO;
import org.example.apifluxar.dto.capacityStock.CapacityStockResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CapacityStockOpenAPI {
    @Operation(summary = "Adicionar a capacidade do estoque",
            description = "Adiciona a capacidade do estoque.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Capacidade do estoque adicionada com sucesso"),
            @ApiResponse( responseCode = "400", description = "Requisição inválida"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<CapacityStockResponseDTO> addCapacityStock(@RequestBody @Validated CapacityStockRequestDTO capacityStockRequestDTO);

    @Operation(summary = "Buscar capacidade do estoque por unidade e setor",
            description = "Retorna os detalhes da capacidade do estoque específica com base na unidade e no setor fornecidos.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Capacidade do estoque encontrada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Capacidade do estoque não encontrada"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<CapacityStockResponseDTO> getByUnitAndSector(@RequestParam Long unitId, @RequestParam Long sectorId);
}
