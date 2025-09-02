package org.example.apifluxar.controller;

import org.example.apifluxar.dto.EmployeeResponseDTO;
import org.example.apifluxar.dto.PlanResponseDTO;
import org.example.apifluxar.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/plan")
public class PlanController {
    final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("search/{id}")
    public ResponseEntity<PlanResponseDTO> selectId(@PathVariable Long id) {
        PlanResponseDTO res = planService.getPlanById(id);
        return ResponseEntity.ok(res);
    }
}
