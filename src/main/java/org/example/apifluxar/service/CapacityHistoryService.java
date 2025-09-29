package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryResponseDTO;
import org.example.apifluxar.dto.industry.IndustryResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.exception.EmptyCapacityHistory;
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
    final IndustryService industryService;

    public CapacityHistoryService(CapacityHistoryRepository capacityHistoryRepository, ObjectMapper objectMapper, IndustryService industryService) {
        this.industryService = industryService;
        this.capacityHistoryRepository = capacityHistoryRepository;
    }

    public List<CapacityHistoryResponseDTO> getCapacityHistoryBySectorAndUnit(Long sectorId, Long unitId) {
        List<CapacityHistory> capacityHistory = capacityHistoryRepository.findBySectorAndAndUnit(sectorId, unitId);

        if (capacityHistory.isEmpty()) {
            throw new EmptyCapacityHistory("Histórico de capacidade vazio nessa unidade e setor");
        }

        List<CapacityHistoryResponseDTO> dtos = new ArrayList<>();
        for (CapacityHistory item : capacityHistory) {
            dtos.add(new CapacityHistoryResponseDTO(
                    item.getTotalCapacity(),
                    item.getFullDate()
            ));
        }

        return dtos;
    }

    public String deleteCapacityHistoryBySectorAndUnit(Long sectorId, Long unitId) {
        Integer deleteQuatidade = capacityHistoryRepository.deleteBySectorAndUnit(sectorId, unitId);
        if (deleteQuatidade == 0) {
            throw new EmptyCapacityHistory("Nenhum histórico de capacidade encontrado para a unidade e setor especificados");
        }
        return "Histórico de capacidade deletado com sucesso: " + deleteQuatidade + " registros." ;
    }
}
