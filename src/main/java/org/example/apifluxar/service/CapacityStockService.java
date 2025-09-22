package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.apifluxar.dto.*;
import org.example.apifluxar.model.CapacityStock;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.repository.CapacityStockRepository;
import org.example.apifluxar.repository.SectorRepository;
import org.example.apifluxar.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CapacityStockService {
    final CapacityStockRepository capacityStockRepository;
    final SectorRepository sectorRepository;
    final UnitRepository unitRepository;
    final ObjectMapper objectMapper;

    public CapacityStockService(CapacityStockRepository capacityStockRepository,SectorRepository sectorRepository, UnitRepository unitRepository, ObjectMapper objectMapper) {
        this.capacityStockRepository = capacityStockRepository;
        this.sectorRepository = sectorRepository;
        this.unitRepository = unitRepository;
        this.objectMapper = objectMapper;
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


    public CapacityStockResposeDTO findById(Long id){
        CapacityStock capacityStock = capacityStockRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
                    unit.getNome(),
                    unit.getCep(),
                    unit.getRua(),
                    unit.getCidade(),
                    unit.getEstado(),
                    unit.getNumero(),
                    unit.getBairro(),
                    objectMapper.convertValue(unit.getIndustry(), IndustryResponseDTO.class)
            );
            unitResponseDTO.setId(unit.getId());
            dto.setUnidade(unitResponseDTO);
        }
        return  dto;
    }

    public List<CapacityStockResposeDTO> getAllCapacityStock(){
        List<CapacityStock> capacityStockAll = capacityStockRepository.findAll();
        List<CapacityStockResposeDTO> dtos =  new ArrayList<>();
        for (CapacityStock capacityStock : capacityStockAll) {
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
                        unit.getNome(),
                        unit.getCep(),
                        unit.getRua(),
                        unit.getCidade(),
                        unit.getEstado(),
                        unit.getNumero(),
                        unit.getBairro(),
                        objectMapper.convertValue(unit.getIndustry(), IndustryResponseDTO.class)
                );
                unitResponseDTO.setId(unit.getId());
                dto.setUnidade(unitResponseDTO);
            }
            dtos.add(dto);
        }
        return dtos;
    }

}
