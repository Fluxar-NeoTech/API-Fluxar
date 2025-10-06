package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.unit.UnitDimensionsResponseDTO;
import org.example.apifluxar.dto.unit.UnitIndustryResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UnitOpenAPI {

    @Operation(summary = "Buscar todas as unidades por ID da indústria",
            description = "Retorna uma lista de todas as unidades associadas a uma indústria específica com base no ID da indústria fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lista de unidades retornada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Indústria não encontrada"),
            @ApiResponse( responseCode = "204", description = "Nenhuma unidade encontrada para a indústria fornecida"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<UnitIndustryResponseDTO>> getUnitByIndustry(@PathVariable Long id);

    @Operation(summary = "Buscar dimensões da unidade por ID da unidade",
            description = "Retorna as dimensões de uma unidade específica com base no ID da unidade fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Dimensões da unidade retornadas com sucesso"),
            @ApiResponse( responseCode = "404", description = "Unidade não encontrada"),
            @ApiResponse( responseCode = "204", description = "Nenhuma dimensão encontrada para a unidade fornecida"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<UnitDimensionsResponseDTO> getUnitByDimensions(@RequestParam Long id);
}
