package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.dto.unit.UnitDimensionsResponseDTO;
import org.example.apifluxar.dto.unit.UnitIndustryResponseDTO;
import org.example.apifluxar.exception.EmptyAvailability;
import org.example.apifluxar.model.*;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.projection.UnitDimensionsProjection;
import org.example.apifluxar.projection.UnitIndustryProjection;
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
    final ObjectMapper objectMapper;

    public UnitService(UnitRepository unitRepository, IndustryService industryService, ObjectMapper objectMapper) {
        this.unitRepository = unitRepository;
        this.industryService = industryService;
        this.objectMapper = objectMapper;
    }

    public UnitResponseDTO getUnitById(Long id) {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        Integer availability = unitRepository.findAvailabilityByUnitId(id);

        UnitResponseDTO dto = new UnitResponseDTO(
                unit.getId(),
                unit.getName(),
                unit.getPostalCode(),
                unit.getStreet(),
                unit.getCity(),
                unit.getState(),
                unit.getNumber(),
                unit.getNeighborhood(),
                unit.getEmail(),
                availability,
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

    public List<UnitIndustryResponseDTO> getUnitByIndustry(Long id) {
        List<Unit> units = unitRepository.findAllByIndustry(id);
        List<UnitIndustryResponseDTO> dtos = new ArrayList<>();
        List<UnitIndustryProjection> projections = new ArrayList<>();

        if (units.isEmpty()) {
            throw new EntityNotFoundException("Não há unidades encontradas nessa indústria");
        }

        projections = unitRepository.findAvailabilityByIndustry(id);

        for (Unit unitItem : units) {

            UnitIndustryProjection projection = projections.stream()
                    .filter(p -> p.getUnidadeId().equals(unitItem.getId()))
                    .findFirst()
                    .orElse(null);

            Integer disponibilidade = projection.getDisponibilidade();

            if (disponibilidade == null) {
                throw new EmptyAvailability("Sem disponibilidade no momento");
            }

            UnitIndustryResponseDTO dto = new UnitIndustryResponseDTO(
                    unitItem.getId(),
                    unitItem.getName(),
                    unitItem.getPostalCode(),
                    unitItem.getStreet(),
                    unitItem.getCity(),
                    unitItem.getState(),
                    unitItem.getNumber(),
                    unitItem.getNeighborhood(),
                    unitItem.getEmail(),
                    projection.getDisponibilidade()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    public UnitDimensionsResponseDTO getUnitDimensions(Long id) {
        UnitDimensionsProjection dimensions = unitRepository.findDimensionsByUnitId(id);

        UnitDimensionsResponseDTO dto = new UnitDimensionsResponseDTO(
                dimensions.getLarguraDisponivel(),
                dimensions.getAlturaDisponivel(),
                dimensions.getComprimentoDisponivel()
        );

        return dto;
    }
}
