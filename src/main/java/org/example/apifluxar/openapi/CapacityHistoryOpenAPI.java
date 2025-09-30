package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CapacityHistoryOpenAPI {
    @Operation(summary = "Listar histórico de capacidade por setor e unidade",
            description = "Retorna uma lista de históricos de capacidade filtrados por setor e unidade.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lista de históricos de capacidade retornada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Setor ou unidade não encontrado"),
            @ApiResponse( responseCode = "400", description = "Nenhum histórico de capacidade encontrado nessa unidade e setor"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<CapacityHistoryResponseDTO>> getCapacityHistoryBySectorAndUnit(@RequestParam Long sectorId,
                                                                                              @RequestParam Long unitId);

    @Operation(summary = "Deletar histórico de capacidade por ID da unidade",
            description = "Deleta todos os históricos de capacidade associados a uma unidade específica com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Históricos de capacidade deletados com sucesso"),
            @ApiResponse( responseCode = "404", description = "Unidade não encontrada"),
            @ApiResponse( responseCode = "400", description = "Nenhum histórico de capacidade encontrado nessa unidade e setor"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<Object> deleteCapacityHistoryBySectorAndUnit(@RequestParam Long unitId, @RequestParam Long sectorId);
}
