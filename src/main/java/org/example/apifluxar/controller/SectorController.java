package org.example.apifluxar.controller;

import org.example.apifluxar.service.SectorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("api/sector")
public class SectorController {
    final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/search/volume/remaining/in/sector/{sectorId}")
    public Double getRemainingVolumeInSector(Long sectorId) {
        return sectorService.getRemainingVolumeInSector(sectorId);
    }

    @GetMapping("/search/volume/used/in/sector/{sectorId}")
    public Double getUsedVolumeInSector(Long sectorId) {
        return sectorService.getUsedVolumeInSector(sectorId);
    }
}
