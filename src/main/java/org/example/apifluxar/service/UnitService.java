package org.example.apifluxar.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.model.*;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {
    final UnitRepository unitRepository;
    final IndustryService industryService;

    public UnitService(UnitRepository unitRepository, IndustryService industryService) {
        this.unitRepository = unitRepository;
        this.industryService = industryService;
    }

    public UnitResponseDTO getUnitById(Long id) {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        UnitResponseDTO dto = new UnitResponseDTO(
                unit.getId(),
                unit.getName(),
                unit.getPostalCode(),
                unit.getStreet(),
                unit.getCity(),
                unit.getState(),
                unit.getNumber(),
                unit.getNeighborhood(),
                industryService.getIndustryById(unit.getIndustry().getId())
        );

        return dto;
    }

//    public UnitBatchResponseDTO getUnitBatchById(Long id) {
//        Unit unit = unitRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        UnitBatchResponseDTO dto = new UnitBatchResponseDTO(
//                unit.getName(),
//                unit.getPostalCode(),
//                unit.getStreet(),
//                unit.getCity(),
//                unit.getState(),
//                unit.getNumber(),
//                unit.getNeighborhood()
//        );
//
//        return dto;
//    }

    public List<UnitResponseDTO> getUnitByIndustry(Long id) {
        List<Unit> unit = unitRepository.findAllByIndustry(id);
        List<UnitResponseDTO> dtos = new ArrayList<>();
        if (unit.isEmpty()) {
            throw new EntityNotFoundException("Unidade não encontrada");
        }
        for (Unit unitItem : unit) {
            UnitResponseDTO dto = new UnitResponseDTO(
                    unitItem.getId(),
                    unitItem.getName(),
                    unitItem.getPostalCode(),
                    unitItem.getStreet(),
                    unitItem.getCity(),
                    unitItem.getState(),
                    unitItem.getNumber(),
                    unitItem.getNeighborhood(),
                    industryService.getIndustryById(unitItem.getIndustry().getId())
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
