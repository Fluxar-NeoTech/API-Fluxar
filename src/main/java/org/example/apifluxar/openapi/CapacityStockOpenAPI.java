package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.capacityStock.CapacityStockRequestDTO;
import org.example.apifluxar.dto.capacityStock.CapacityStockResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CapacityStockOpenAPI {

    @Operation(summary = "Adiciona ou atualiza a capacidade de um setor em uma unidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Capacidade adicionada ou atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<MessageResponseDTO> addCapacityStock(@RequestBody @Validated CapacityStockRequestDTO capacityStockRequestDTO);
}
