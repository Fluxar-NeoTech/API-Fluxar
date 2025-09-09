package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.SectorResponseDTO;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.repository.SectorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SectorService {
    final SectorRepository sectorRepository;
    final ObjectMapper objectMapper;

    public SectorService(SectorRepository sectorRepository, ObjectMapper objectMapper) {
        this.sectorRepository = sectorRepository;
        this.objectMapper = objectMapper;
    }

    public SectorResponseDTO getSectorById(Long id) {
        Sector sector = sectorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(sector, SectorResponseDTO.class);
    }
}
