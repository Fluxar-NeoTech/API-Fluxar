package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.service.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/unit")
public class UnitController {
    final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "Buscar unidade por ID",
            description = "Retorna os detalhes de uma unidade específica com base no ID fornecido.")
    public ResponseEntity<UnitResponseDTO> getUnitById(@PathVariable Long id) {
        UnitResponseDTO res = unitService.getUnitById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/searchAll/Unit/Industry/{id}")
    @Operation(summary = "Buscar todas as unidades por ID da indústria",
            description = "Retorna uma lista de todas as unidades associadas a uma indústria específica com base no ID da indústria fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lista de unidades retornada com sucesso"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<UnitResponseDTO>> getUnitByIndustry(@PathVariable Long id) {
        List<UnitResponseDTO> res = unitService.getUnitByIndustry(id);
        return ResponseEntity.ok(res);
    }
}
