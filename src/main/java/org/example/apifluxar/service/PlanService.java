package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.PlanResponseDTO;
import org.example.apifluxar.model.Plan;
import org.example.apifluxar.repository.PlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlanService {
    final PlanRepository planRepository;
    final ObjectMapper objectMapper;

    public PlanService(PlanRepository planRepository, ObjectMapper objectMapper) {
        this.planRepository = planRepository;
        this.objectMapper = objectMapper;
    }

    public PlanResponseDTO getPlanById(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(plan, PlanResponseDTO.class);
    }
}
