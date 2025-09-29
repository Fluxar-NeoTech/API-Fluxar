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
                unit.getNome(),
                unit.getCep(),
                unit.getRua(),
                unit.getCidade(),
                unit.getEstado(),
                unit.getNumero(),
                unit.getBairro(),
                industryService.getIndustryById(unit.getIndustry().getId())
        );

        return dto;
    }

    public List<UnitResponseDTO> getUnitByIndustryId(Long id) {
        List<Unit> unit = unitRepository.findAllByIndustryId(id);
        List<UnitResponseDTO> dtos = new ArrayList<>();
        if (unit.isEmpty()) {
            throw new EntityNotFoundException("Unidade não encontrada");
        }
        for (Unit unitItem : unit) {
            UnitResponseDTO dto = new UnitResponseDTO(
                    unitItem.getId(),
                    unitItem.getNome(),
                    unitItem.getCep(),
                    unitItem.getRua(),
                    unitItem.getCidade(),
                    unitItem.getEstado(),
                    unitItem.getNumero(),
                    unitItem.getBairro(),
                    industryService.getIndustryById(unitItem.getIndustry().getId())


            );
            dtos.add(dto);
        }
        return dtos;
    }
}
