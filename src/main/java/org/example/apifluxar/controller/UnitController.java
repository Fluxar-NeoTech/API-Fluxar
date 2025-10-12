package org.example.apifluxar.controller;

import org.example.apifluxar.dto.unit.UnitIndustryResponseDTO;
import org.example.apifluxar.openapi.UnitOpenAPI;
import org.example.apifluxar.service.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/unit")
public class UnitController implements UnitOpenAPI {
    final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/search/all/by/industry/{id}")
    public ResponseEntity<List<UnitIndustryResponseDTO>> getUnitByIndustry(@PathVariable Long id) {
        List<UnitIndustryResponseDTO> res = unitService.getUnitByIndustry(id);
        return ResponseEntity.ok(res);
    }
}
