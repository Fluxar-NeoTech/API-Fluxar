package org.example.apifluxar.service;

import org.example.apifluxar.dto.SectorResponseDTO;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.repository.SectorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SectorService {
    final SectorRepository sectorRepository;
    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }
    public SectorResponseDTO fromSectorResponseDTO(Sector sector) {
        SectorResponseDTO sectorResponseDTO = new SectorResponseDTO();
        sectorResponseDTO.setId(sector.getId());
        sectorResponseDTO.setNome(sector.getNome());
        sectorResponseDTO.setDescricao(sector.getDescricao());
        return sectorResponseDTO;
    }

    public SectorResponseDTO getSectorById(long id) {
        Sector sector = sectorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return fromSectorResponseDTO(sector);
    }

    public SectorResponseDTO getSectorByName(String name) {
        Sector sector = sectorRepository.findByNome(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return fromSectorResponseDTO(sector);
    }
}
