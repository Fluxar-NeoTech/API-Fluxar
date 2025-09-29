package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.capacityStock.CapacityStockRequestDTO;
import org.example.apifluxar.dto.capacityStock.CapacityStockResposeDTO;
import org.example.apifluxar.service.CapacityStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/capacityStock")
public class CapacityStockController {
    private final CapacityStockService capacityStockService;

    @Autowired
    public CapacityStockController(CapacityStockService capacityStockService) {
        this.capacityStockService = capacityStockService;
    }

    @PostMapping("/add")
    @Operation(summary = "Adicionar a capacidade do estoque",
            description = "Adiciona a capacidade do estoque.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Capacidade do estoque adicionada com sucesso"),
            @ApiResponse( responseCode = "400", description = "Requisição inválida"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CapacityStockResposeDTO> addCapacityStock(@RequestBody @Validated CapacityStockRequestDTO capacityStockRequestDTO){
        CapacityStockResposeDTO capacityStockResposeDTO = capacityStockService.addCapacityStock(capacityStockRequestDTO);
        return ResponseEntity.ok(capacityStockResposeDTO);
    }

    @GetMapping("/search/by/unit/{unitId}/sector/{sectorId}")
    @Operation(summary = "Buscar capacidade do estoque por ID da unidade",
            description = "Retorna os detalhes da capacidade do estoque específica com base no ID da unidade fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Capacidade do estoque encontrada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Capacidade do estoque não encontrada"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CapacityStockResposeDTO> getByUnitAndSector(@RequestParam Long unitId, @RequestParam Long sectorId){
        CapacityStockResposeDTO dto = capacityStockService.getByUnitAndSector(unitId, sectorId);
        return ResponseEntity.ok(dto);
    }
}
