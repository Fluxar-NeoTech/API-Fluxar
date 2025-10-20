package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;

public interface SectorOpenAPI {

    @Operation(summary = "Busca o volume restante em um setor para um funcionário específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Volume restante encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Setor ou funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    Double getRemainingVolumeInSector(@RequestParam Long sectorId, @RequestParam Long employeeId);

    @Operation(summary = "Busca o volume utilizado em um setor para um funcionário específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Volume utilizado encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Setor ou funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    Double getUsedVolumeInSector(@RequestParam Long sectorId, @RequestParam Long employeeId);
}
