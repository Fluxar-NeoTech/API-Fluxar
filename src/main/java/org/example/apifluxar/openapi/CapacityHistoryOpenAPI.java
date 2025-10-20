package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryOccupationResponse;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CapacityHistoryOpenAPI {
    @Operation(summary = "Pesquisa o histórico de capacidade por ID do setor e ID da unidade",
            description = "Retorna uma lista de históricos de capacidade para o setor e unidade fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico de capacidade encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Histórico de capacidade não encontrado para o setor e unidade fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<CapacityHistoryResponseDTO> getCapacityHistoryByUnit(@PathVariable Long unitId);

    @Operation(summary = "Pesquisa a ocupação da capacidade do setor por ID do setor e ID do funcionário",
            description = "Retorna a ocupação da capacidade do setor para o setor e funcionário fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ocupação da capacidade do setor encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ocupação da capacidade do setor não encontrada para o setor e funcionário fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<CapacityHistoryOccupationResponse> getSectorCapacityUsage(@RequestParam Long sectorId, @RequestParam Long employeeId);
}
