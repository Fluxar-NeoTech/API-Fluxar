package org.example.apifluxar.controller;

import org.example.apifluxar.dto.SectorResponseDTO;
import org.example.apifluxar.service.SectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class SectorController {
    final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<SectorResponseDTO> searchSector(@PathVariable Long id) {
        SectorResponseDTO res = sectorService.getSectorById(id);
        return ResponseEntity.ok(res);
    }
}
