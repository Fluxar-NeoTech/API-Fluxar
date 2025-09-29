package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.server.ResponseStatusException;

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

    public CapacityStockService(CapacityStockRepository capacityStockRepository,
                                SectorRepository sectorRepository,
                                UnitRepository unitRepository,
                                IndustryService industryService,
                                ObjectMapper objectMapper,
                                UnitService unitService,
                                SectorService sectorService) {
        this.sectorService = sectorService;
        this.unitService = unitService;
        this.objectMapper = objectMapper;
        this.capacityStockRepository = capacityStockRepository;
        this.sectorRepository = sectorRepository;
        this.unitRepository = unitRepository;
        this.industryService = industryService;
    }

    @Transactional
    public CapacityStockResponseDTO addCapacityStock(CapacityStockRequestDTO capacityStockRequestDTO) {
        CapacityStock capacityStock = objectMapper.convertValue(capacityStockRequestDTO, CapacityStock.class);

        Sector setor = sectorRepository.findById(capacityStockRequestDTO.getSetorId())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

        Unit unidade = unitRepository.findById(capacityStockRequestDTO.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        Optional<CapacityStock> exist = capacityStockRepository.findBySetorAndUnidade(setor, unidade);

        //se ja existir um registro, atualiza
        if (exist.isPresent()) {
            CapacityStock existingStock = exist.get();
            existingStock.setHeight(capacityStock.getHeight());
            existingStock.setWidth(capacityStock.getWidth());
            existingStock.setLength(capacityStock.getLength());


            existingStock.setMaxCapacity(
                    existingStock.getWidth() * existingStock.getHeight() * existingStock.getLength()
            );

            CapacityStock updated = capacityStockRepository.save(existingStock);
            return objectMapper.convertValue(updated, CapacityStockResponseDTO.class);

            //se nao existir, cria novo
        } else {
            capacityStock.setSector(setor);
            capacityStock.setUnit(unidade);
            capacityStock.setMaxCapacity(
                    capacityStock.getWidth() * capacityStock.getHeight() * capacityStock.getLength()
            );

            CapacityStock saved = capacityStockRepository.save(capacityStock);
            return objectMapper.convertValue(saved, CapacityStockResponseDTO.class);
        }
    }


    public CapacityStockResponseDTO getByUnitAndSector(Long unidadeId, Long sectorId) {
        CapacityStock capacityStock = capacityStockRepository.findBySectorAndUnidade(unidadeId, sectorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CapacityStockResponseDTO dto = new CapacityStockResponseDTO(
                capacityStock.getHeight(),
                capacityStock.getMaxCapacity(),
                capacityStock.getLength(),
                capacityStock.getWidth()

        );
        Sector setor = capacityStock.getSector();
        if (setor != null) {
            SectorResponseDTO sectorResponseDTO = sectorService.getSectorById(setor.getId());
            dto.setSetor(sectorResponseDTO);
        }

        Unit unit = capacityStock.getUnit();
        if (unit != null) {
            UnitResponseDTO unitResponseDTO = unitService.getUnitById(unit.getId());
            dto.setUnidade(unitResponseDTO);
        }
        return  dto;
    }
}
