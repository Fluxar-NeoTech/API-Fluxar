package org.example.apifluxar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryResponseDTO;
import org.example.apifluxar.service.CapacityHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/capacityHistory")
public class CapacityHistoryController {
    final CapacityHistoryService capacityHistoryService;
    final ObjectMapper objectMapper;

    public CapacityHistoryController(CapacityHistoryService capacityHistoryService, ObjectMapper objectMapper) {
        this.capacityHistoryService = capacityHistoryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/search/by/sector/{sectorId}/unit/{unitId}")
    @Operation(summary = "Listar histórico de capacidade por setor e unidade",
            description = "Retorna uma lista de históricos de capacidade filtrados por setor e unidade.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lista de históricos de capacidade retornada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Setor ou unidade não encontrado"),
            @ApiResponse( responseCode = "400", description = "Nenhum histórico de capacidade encontrado nessa unidade e setor"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<CapacityHistoryResponseDTO>> getCapacityHistoryBySectorAndUnit(@RequestParam Long sectorId,
                                                                                          @RequestParam Long unitId) {
        List<CapacityHistoryResponseDTO> res = capacityHistoryService.getCapacityHistoryBySectorAndUnit( sectorId, unitId);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/by/sector/{sectorId}/unit/{unitId}")
    @Operation(summary = "Deletar histórico de capacidade por ID da unidade",
            description = "Deleta todos os históricos de capacidade associados a uma unidade específica com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Históricos de capacidade deletados com sucesso"),
            @ApiResponse( responseCode = "404", description = "Unidade não encontrada"),
            @ApiResponse( responseCode = "400", description = "Nenhum histórico de capacidade encontrado nessa unidade e setor"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Object> deleteCapacityHistoryBySectorAndUnit(@RequestParam Long unitId, @RequestParam Long sectorId) {
        String message = capacityHistoryService.deleteCapacityHistoryBySectorAndUnit(sectorId, unitId);
        return ResponseEntity.ok(message);
    }
}
