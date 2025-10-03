package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.openapi.UnitOpenAPI;
import org.example.apifluxar.service.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/unit")
public class UnitController{
    final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

//    @GetMapping("/search/by/id/{id}")
//    public ResponseEntity<UnitResponseDTO> getUnitById(@PathVariable Long id) {
//        UnitResponseDTO res = unitService.getUnitById(id);
//        return ResponseEntity.ok(res);
//    }

    @GetMapping("/search/all/by/industry/{id}")
    public ResponseEntity<List<UnitResponseDTO>> getUnitByIndustry(@PathVariable Long id) {
        List<UnitResponseDTO> res = unitService.getUnitByIndustry(id);
        return ResponseEntity.ok(res);
    }
}
