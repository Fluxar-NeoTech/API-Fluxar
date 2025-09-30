package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.repository.SectorRepository;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

    final private SectorRepository sectorRepository;
    final private ObjectMapper objectMapper;

    public SectorService(SectorRepository sectorRepository, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.sectorRepository = sectorRepository;
    }

    public SectorResponseDTO getSectorById(Long id) {
        Sector setor = sectorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Setor não encontrado"));
        return objectMapper.convertValue(setor, SectorResponseDTO.class);
    }
}
