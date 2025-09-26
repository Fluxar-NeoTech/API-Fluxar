package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.CapacityHistoryResponseDTO;
import org.example.apifluxar.dto.IndustryResponseDTO;
import org.example.apifluxar.model.*;
import org.example.apifluxar.dto.UnitResponseDTO;
import org.example.apifluxar.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {
    final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public UnitResponseDTO getUnitById(Long id) {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        UnitResponseDTO dto = new UnitResponseDTO(
                unit.getNome(),
                unit.getCep(),
                unit.getRua(),
                unit.getCidade(),
                unit.getEstado(),
                unit.getNumero(),
                unit.getBairro()
        );
        dto.setId(id);

        Industry industry = unit.getIndustry();
        if (industry != null) {
            IndustryResponseDTO industryDTO = new IndustryResponseDTO(
                    industry.getNome(),
                    industry.getCnpj()
            );

            dto.setIndustry(industryDTO);
        }

        return dto;
    }

    public List<UnitResponseDTO> getUnitByIndustryId(Long id) {
        List<Unit> unit = unitRepository.findAllByIndustryId(id);
        List<UnitResponseDTO> dtos = new ArrayList<>();
        for (Unit unitItem : unit) {
            UnitResponseDTO dto = new UnitResponseDTO(
                    unitItem.getNome(),
                    unitItem.getCep(),
                    unitItem.getRua(),
                    unitItem.getCidade(),
                    unitItem.getEstado(),
                    unitItem.getNumero(),
                    unitItem.getBairro()
            );
            dto.setId(id);

            Industry industry = unitItem.getIndustry();
            if (industry != null) {
                IndustryResponseDTO industryDTO = new IndustryResponseDTO(
                        industry.getNome(),
                        industry.getCnpj()
                );

                dto.setIndustry(industryDTO);
            }
            dtos.add(dto);
        }
        return dtos;
    }
}
