package org.example.apifluxar.controller;

import org.example.apifluxar.dto.IndustryResponseDTO;
import org.example.apifluxar.dto.PlanResponseDTO;
import org.example.apifluxar.service.IndustryService;
import org.slf4j.helpers.CheckReturnValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/industry")
public class IndustryController {
    final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @GetMapping
    public ResponseEntity<IndustryResponseDTO> selectId(@PathVariable Long id) {
        IndustryResponseDTO res = industryService.getPlanById(id);
        return ResponseEntity.ok(res);
    }
}
