package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.stockHistory.StockHistoryResponseDTO;
import org.example.apifluxar.openapi.StockHistoryOpenAPI;
import org.example.apifluxar.service.StockHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/stockHistory")
public class StockHistoryController implements StockHistoryOpenAPI {
    final StockHistoryService stockHistoryService;

    public StockHistoryController(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping("search/by/unit/sector")
    public ResponseEntity<StockHistoryResponseDTO> getStockHistoryById(@RequestParam Long unitId, @RequestParam Long sectorId) {
        StockHistoryResponseDTO dto = stockHistoryService.getStockHistoryById(unitId, sectorId);
        return ResponseEntity.ok(dto);
    }
}
