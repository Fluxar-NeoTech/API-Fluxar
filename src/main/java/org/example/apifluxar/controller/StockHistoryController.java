package org.example.apifluxar.controller;

import org.example.apifluxar.dto.StockHistoryResponseDTO;
import org.example.apifluxar.service.StockHistoryService;
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
    public StockHistoryResponseDTO selectById(@PathVariable Long id) {
        StockHistoryResponseDTO stockHistoryResponseDTO = stockHistoryService.getStockHistoryById(id);
        return stockHistoryResponseDTO;
    }
}
