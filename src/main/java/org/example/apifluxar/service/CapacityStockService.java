package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.apifluxar.dto.capacityStock.CapacityStockRequestDTO;
import org.example.apifluxar.dto.capacityStock.CapacityStockResposeDTO;
import org.example.apifluxar.dto.industry.IndustryResponseDTO;
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

@Service
public class CapacityStockService {
    final CapacityStockRepository capacityStockRepository;
    final SectorRepository sectorRepository;
    final UnitRepository unitRepository;
    final IndustryService industryService;
    final ObjectMapper objectMapper;

    public CapacityStockService(CapacityStockRepository capacityStockRepository,
                                SectorRepository sectorRepository,
                                UnitRepository unitRepository,
                                IndustryService industryService,
                                ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.capacityStockRepository = capacityStockRepository;
        this.sectorRepository = sectorRepository;
        this.unitRepository = unitRepository;
        this.industryService = industryService;
    }

    @Transactional
    public CapacityStockResposeDTO addCapacityStock(CapacityStockRequestDTO capacityStockRequestDTO) {
        CapacityStock capacityStock = objectMapper.convertValue(capacityStockRequestDTO, CapacityStock.class);

        Sector setor = sectorRepository.findById(capacityStockRequestDTO.getSetorId())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado"));
        Unit unidade = unitRepository.findById(capacityStockRequestDTO.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        capacityStock.setSetor(setor);
        capacityStock.setUnidade(unidade);

        capacityStock.setCapacidadeMaxima(
                capacityStock.getLargura() * capacityStock.getAltura() * capacityStock.getComprimento()
        );

        CapacityStock saved = capacityStockRepository.save(capacityStock);

        return objectMapper.convertValue(saved, CapacityStockResposeDTO.class);
    }

    public CapacityStockResposeDTO findByUnidadeIdAndSectorId(Long unidadeId, Long sectorId) {
        CapacityStock capacityStock = capacityStockRepository.findBySectorAndUnidade(unidadeId, sectorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CapacityStockResposeDTO dto = new CapacityStockResposeDTO(
                capacityStock.getAltura(),
                capacityStock.getCapacidadeMaxima(),
                capacityStock.getComprimento(),
                capacityStock.getLargura()
        );
        Sector setor = capacityStock.getSetor();
        if (setor != null) {
            SectorResponseDTO sectorResponseDTO = new SectorResponseDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getDescricao()
            );
            dto.setSetor(sectorResponseDTO);
        }

        Unit unit = capacityStock.getUnidade();
        if (unit != null) {
            UnitResponseDTO unitResponseDTO = new UnitResponseDTO (
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
            unitResponseDTO.setId(unit.getId());
            dto.setUnidade(unitResponseDTO);
        }
        return  dto;
    }

}
