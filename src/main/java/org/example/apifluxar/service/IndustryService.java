package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.dto.industry.IndustryResponseDTO;
import org.example.apifluxar.model.Industry;
import org.example.apifluxar.repository.IndustryRepository;
import org.springframework.stereotype.Service;

@Service
public class IndustryService {
    final IndustryRepository industryRepository;
    final ObjectMapper objectMapper;

    public IndustryService(IndustryRepository industryRepository, ObjectMapper objectMapper) {
        this.industryRepository = industryRepository;
        this.objectMapper = objectMapper;
    }

    public IndustryResponseDTO getIndustryById(Long id) {
        Industry industry = industryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Indústria não encontrada"));
        return objectMapper.convertValue(industry, IndustryResponseDTO.class);
    }
}
