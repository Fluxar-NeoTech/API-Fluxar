package org.example.apifluxar.controller;

import org.example.apifluxar.model.Batch;
import org.example.apifluxar.service.BatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batch")
public class BatchController {
    final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/findById/{id}")
    public Batch findById(@PathVariable Long id){
         return batchService.findById(id);
    }

}
