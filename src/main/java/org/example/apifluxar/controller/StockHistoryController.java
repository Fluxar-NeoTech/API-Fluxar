package org.example.apifluxar.controller;

import org.example.apifluxar.dto.StockHistoryResponseDTO;
import org.example.apifluxar.service.StockHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/stockhistory")
public class StockHistoryController {
    final StockHistoryService stockHistoryService;

    public StockHistoryController(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping("search/id/{id}")
    public ResponseEntity<StockHistoryResponseDTO> selectId(@PathVariable Long id) {
        StockHistoryResponseDTO dto = stockHistoryService.getStockHistoryById(id);
        return ResponseEntity.ok(dto);
    }
}
