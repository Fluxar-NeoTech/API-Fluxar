package org.example.apifluxar.service;

import org.example.apifluxar.model.Batch;
import org.example.apifluxar.repository.BatchRepository;
import org.example.apifluxar.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BatchService {
    final BatchRepository batchRepository;
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }
    public Batch findById(Long id){
       return batchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
