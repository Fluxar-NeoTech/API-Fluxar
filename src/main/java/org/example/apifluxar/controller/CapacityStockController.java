package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.capacityStock.CapacityStockRequestDTO;
import org.example.apifluxar.dto.capacityStock.CapacityStockResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.openapi.CapacityStockOpenAPI;
import org.example.apifluxar.service.CapacityStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/capacityStock")
public class CapacityStockController implements CapacityStockOpenAPI {
    private final CapacityStockService capacityStockService;

    @Autowired
    public CapacityStockController(CapacityStockService capacityStockService) {
        this.capacityStockService = capacityStockService;
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponseDTO> addCapacityStock(@RequestBody @Validated CapacityStockRequestDTO capacityStockRequestDTO){
        MessageResponseDTO messageResponseDTO = capacityStockService.addOrUpdateCapacityStock(capacityStockRequestDTO);
        return ResponseEntity.ok(messageResponseDTO);
    }

//    @GetMapping("/search/by/unit/sector")
//    public ResponseEntity<CapacityStockResponseDTO> getByUnitAndSector(@RequestParam Long unitId, @RequestParam Long sectorId){
//        CapacityStockResponseDTO dto = capacityStockService.getByUnitAndSector(unitId, sectorId);
//        return ResponseEntity.ok(dto);
//    }
}
