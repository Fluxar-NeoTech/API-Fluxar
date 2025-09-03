package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.model.*;
import org.example.apifluxar.dto.UnitResponseDTO;
import org.example.apifluxar.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UnitService {
    final UnitRepository unitRepository;
    final ObjectMapper objectMapper;

    public UnitService(UnitRepository unitRepository, ObjectMapper objectMapper) {
        this.unitRepository = unitRepository;
        this.objectMapper = objectMapper;
    }

    public UnitResponseDTO getUnitById(Long id) {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(unit, UnitResponseDTO.class);
    }
}
