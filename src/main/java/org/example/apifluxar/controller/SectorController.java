package org.example.apifluxar.controller;

import org.example.apifluxar.service.SectorService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/sector")
public class SectorController {
    final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/search/volume/remaining/in/sector/{sectorId}")
    public Double getRemainingVolumeInSector(@PathVariable Long sectorId) {
        return sectorService.getRemainingVolumeInSector(sectorId);
    }

    @GetMapping("/search/volume/used/in/sector/{sectorId}")
    public Double getUsedVolumeInSector(@PathVariable Long sectorId) {
        return sectorService.getUsedVolumeInSector(sectorId);
    }
}
