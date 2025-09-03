package org.example.apifluxar.controller;

import org.example.apifluxar.dto.UnitResponseDTO;
import org.example.apifluxar.service.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/unit")
public class UnitController {
    final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("search/{id}")
    public ResponseEntity<UnitResponseDTO> searchUnit(@PathVariable Long id) {
        UnitResponseDTO res = unitService.getUnitById(id);
        return ResponseEntity.ok(res);
    }
}
