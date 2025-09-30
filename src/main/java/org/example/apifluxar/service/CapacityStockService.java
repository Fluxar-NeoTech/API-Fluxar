package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.apifluxar.dto.capacityStock.CapacityStockRequestDTO;
import org.example.apifluxar.dto.capacityStock.CapacityStockResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.model.CapacityStock;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.repository.CapacityStockRepository;
import org.example.apifluxar.repository.SectorRepository;
import org.example.apifluxar.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CapacityStockService {
    final CapacityStockRepository capacityStockRepository;
    final SectorRepository sectorRepository;
    final SectorService sectorService;
    final UnitRepository unitRepository;
    final UnitService unitService;
    final IndustryService industryService;
    final ObjectMapper objectMapper;
    final EntityManager entityManager;

    public CapacityStockService(CapacityStockRepository capacityStockRepository,
                                SectorRepository sectorRepository,
                                UnitRepository unitRepository,
                                IndustryService industryService,
                                ObjectMapper objectMapper,
                                UnitService unitService,
                                SectorService sectorService,
                                EntityManager entityManager) {
        this.sectorService = sectorService;
        this.unitService = unitService;
        this.objectMapper = objectMapper;
        this.capacityStockRepository = capacityStockRepository;
        this.sectorRepository = sectorRepository;
        this.unitRepository = unitRepository;
        this.industryService = industryService;
        this.entityManager = entityManager;
    }

    @Transactional
    public CapacityStockResponseDTO addOrUpdateCapacityStock(CapacityStockRequestDTO dto) {
        Sector sector = sectorRepository.findById(dto.getSectorId())
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado"));

        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        Optional<CapacityStock> existingOpt = capacityStockRepository.findBySectorAndUnit(sector, unit);

        CapacityStock capacityStock;

        if (existingOpt.isPresent()) {
            // Atualiza registro existente
            capacityStock = existingOpt.get();
            capacityStock.setHeight(dto.getHeight());
            capacityStock.setWidth(dto.getWidth());
            capacityStock.setLength(dto.getLength());

            capacityStock = capacityStockRepository.saveAndFlush(capacityStock);
        } else {
            // Cria novo registro
            capacityStock = new CapacityStock();
            capacityStock.setHeight(dto.getHeight());
            capacityStock.setWidth(dto.getWidth());
            capacityStock.setLength(dto.getLength());
            capacityStock.setSector(sector);
            capacityStock.setUnit(unit);

            capacityStock = capacityStockRepository.saveAndFlush(capacityStock);
        }

        // Força Hibernate a recalcular campos com @Formula
        entityManager.refresh(capacityStock);

        return objectMapper.convertValue(capacityStock, CapacityStockResponseDTO.class);
    }

    public CapacityStockResponseDTO getByUnitAndSector(Long unitId, Long sectorId) {
        CapacityStock capacityStock = capacityStockRepository.findBySectorAndUnit(unitId, sectorId).orElseThrow(() -> new EntityNotFoundException(
                "Capacidade de estoque não encontrada para a unidade e setor informados"));
        CapacityStockResponseDTO dto = new CapacityStockResponseDTO(
                capacityStock.getHeight(),
                capacityStock.getMaxCapacity(),
                capacityStock.getLength(),
                capacityStock.getWidth());

        Sector setor = capacityStock.getSector();
        if (setor != null) {
            SectorResponseDTO sectorResponseDTO = sectorService.getSectorById(setor.getId());
            dto.setSector(sectorResponseDTO);
        }

        Unit unit = capacityStock.getUnit();
        if (unit != null) {
            UnitResponseDTO unitResponseDTO = unitService.getUnitById(unit.getId());
            dto.setUnit(unitResponseDTO);
        }
        return  dto;
    }
}
