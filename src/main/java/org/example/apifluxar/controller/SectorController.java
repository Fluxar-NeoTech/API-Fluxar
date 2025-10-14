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

    @GetMapping("/search/volume/remaining/in/sector/employee")
    public Double getRemainingVolumeInSector(@RequestParam Long sectorId, @RequestParam Long employeeId) {
        return sectorService.getRemainingVolumeInSector(sectorId, employeeId);
    }

    @GetMapping("/search/volume/used/in/sector/employee")
    public Double getUsedVolumeInSector(@RequestParam Long sectorId, @RequestParam Long employeeId) {
        return sectorService.getUsedVolumeInSector(sectorId, employeeId);
    }
}
