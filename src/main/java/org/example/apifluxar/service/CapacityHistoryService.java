package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryOccupationResponse;
import org.example.apifluxar.dto.capacityHistory.CapacityHistoryResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.exception.EmptyCapacityHistory;
import org.example.apifluxar.model.*;
import org.example.apifluxar.projection.CapacityHistoryProjection;
import org.example.apifluxar.repository.CapacityHistoryRepository;
import org.springframework.stereotype.Service;
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

    public CapacityHistoryResponseDTO getCapacityHistoryBySectorAndUnit(Long sectorId, Long unitId) {
        List<CapacityHistory> capacityHistory = capacityHistoryRepository.findBySectorAndAndUnit(sectorId, unitId);

        if (capacityHistory.isEmpty()) {
            throw new EmptyCapacityHistory("Histórico de capacidade vazio nessa unidade e setor");
        }

        List<CapacityHistoryResponseDTO> dto = new ArrayList<>();
        for (CapacityHistory item : capacityHistory) {
            dto.add(new CapacityHistoryResponseDTO(
                    item.getTotalCapacity(),
                    item.getFullDate(),
                    item.getOccupancyPercentage()
            ));
        }

        return dto.getLast();
    }

    public CapacityHistoryOccupationResponse getSectorCapacityUsage(Long sectorId, Long employeeId) {
        CapacityHistoryProjection projection = capacityHistoryRepository.getSectorCapacityUsage(sectorId, employeeId);

        return new CapacityHistoryOccupationResponse(
                projection.getOccupancyPercentage(),
                projection.getRemainingVolume()
        );
    }
}
