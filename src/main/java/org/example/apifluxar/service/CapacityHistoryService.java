package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryResponseDTO;
import org.example.apifluxar.dto.industry.IndustryResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.model.*;
import org.example.apifluxar.repository.CapacityHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CapacityHistoryService {
    final CapacityHistoryRepository capacityHistoryRepository;
    final ObjectMapper objectMapper;

    public CapacityHistoryService(CapacityHistoryRepository capacityHistoryRepository, ObjectMapper objectMapper) {
        this.capacityHistoryRepository = capacityHistoryRepository;
        this.objectMapper = objectMapper;
    }

    public CapacityHistoryResponseDTO getCapacityHistoryById(Long id) {
        CapacityHistory capacityHistory = capacityHistoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CapacityHistoryResponseDTO dto = new CapacityHistoryResponseDTO(
                capacityHistory.getCapacidadeTotal(),
                capacityHistory.getDataCompleta()
        );
        Sector setor = capacityHistory.getSector();
        if (setor != null) {
            SectorResponseDTO sectorDTO = new SectorResponseDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getDescricao()
            );
            dto.setSector(sectorDTO);
        }
        Unit unit = capacityHistory.getUnidade();
        if (unit != null) {
            UnitResponseDTO unitDTO = new UnitResponseDTO(
                    unit.getNome(),
                    unit.getCep(),
                    unit.getRua(),
                    unit.getCidade(),
                    unit.getEstado(),
                    unit.getNumero(),
                    unit.getBairro(),
                    objectMapper.convertValue(unit.getIndustry(), IndustryResponseDTO.class)

            );
            unitDTO.setId(unit.getId());
            dto.setUnidade(unitDTO);
        }
        return dto;
    }

    public List<CapacityHistoryResponseDTO> getCapacityHistoryBySectorAndUnidade(Long setorId,Long unidadeId) {
        List<CapacityHistory> capacityHistory = capacityHistoryRepository.findBySectorAndAndUnidade( setorId, unidadeId);
        List<CapacityHistoryResponseDTO> dtos = new ArrayList<>();
        for(CapacityHistory capacityHistoryItem : capacityHistory) {
            CapacityHistoryResponseDTO dto = new CapacityHistoryResponseDTO(
                    capacityHistoryItem.getCapacidadeTotal(),
                    capacityHistoryItem.getDataCompleta()
            );
            Sector setor = capacityHistoryItem.getSector();
            if (setor != null) {
                SectorResponseDTO sectorDTO = new SectorResponseDTO(
                        setor.getId(),
                        setor.getNome(),
                        setor.getDescricao()
                );
                dto.setSector(sectorDTO);
            }
            Unit unit = capacityHistoryItem.getUnidade();
            if (unit != null) {
                UnitResponseDTO unitDTO = new UnitResponseDTO(
                        unit.getNome(),
                        unit.getCep(),
                        unit.getRua(),
                        unit.getCidade(),
                        unit.getEstado(),
                        unit.getNumero(),
                        unit.getBairro(),
                        objectMapper.convertValue(unit.getIndustry(),IndustryResponseDTO.class)
                );
                unitDTO.setId(unit.getId());
                dto.setUnidade(unitDTO);
            }
            dtos.add(dto);
        }
        return dtos;
    }


    public int deleteCapacityHistoryByIdSetor(Long idSetor) {
        int deleteQuatidade = capacityHistoryRepository.deleteBySector(idSetor);
        if (deleteQuatidade == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return deleteQuatidade;
    }

    public int deleteCapacityHistoryByIdUnidade(Long idUnidade) {
        int deleteQuatidade = capacityHistoryRepository.deleteByUnidade(idUnidade);
        if (deleteQuatidade == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return deleteQuatidade;
    }
}
