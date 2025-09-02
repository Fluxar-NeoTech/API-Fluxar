package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.IndustryResponseDTO;
import org.example.apifluxar.dto.PlanResponseDTO;
import org.example.apifluxar.model.Industry;
import org.example.apifluxar.model.Plan;
import org.example.apifluxar.repository.IndustryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IndustryService {
    final IndustryRepository industryRepository;
    final ObjectMapper objectMapper;

    public IndustryService(IndustryRepository industryRepository, ObjectMapper objectMapper) {
        this.industryRepository = industryRepository;
        this.objectMapper = objectMapper;
    }

    public IndustryResponseDTO getPlanById(Long id) {
        Industry industry = industryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(industry, IndustryResponseDTO.class);
    }
}
