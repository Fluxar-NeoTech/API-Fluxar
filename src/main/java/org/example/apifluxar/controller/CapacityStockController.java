package org.example.apifluxar.controller;

import org.example.apifluxar.dto.CapacityStockRequestDTO;
import org.example.apifluxar.dto.CapacityStockResposeDTO;
import org.example.apifluxar.service.CapacityStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/capacityStock")
public class CapacityStockController {
    private final CapacityStockService capacityStockService;

    @Autowired
    public CapacityStockController(CapacityStockService capacityStockService) {
        this.capacityStockService = capacityStockService;
    }

    @PostMapping("/addCapacityStock")
    public ResponseEntity<CapacityStockResposeDTO> addCapacityStock(@RequestBody @Validated CapacityStockRequestDTO capacityStockRequestDTO){
        CapacityStockResposeDTO capacityStockResposeDTO = capacityStockService.addCapacityStock(capacityStockRequestDTO);
        return ResponseEntity.ok(capacityStockResposeDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CapacityStockResposeDTO>> getAllCapacityStock(){
        List<CapacityStockResposeDTO> capacityStockResposeDTOS = capacityStockService.getAllCapacityStock();
        return ResponseEntity.ok(capacityStockResposeDTOS);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<CapacityStockResposeDTO> searchId(@PathVariable Long id){
        CapacityStockResposeDTO capacityStockResposeDTO = capacityStockService.findById(id);
        return ResponseEntity.ok(capacityStockResposeDTO);
    }
}
