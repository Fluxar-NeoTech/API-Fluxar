package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.BatchRequestDTO;
import org.example.apifluxar.dto.BatchResposeDTO;
import org.example.apifluxar.dto.EmployeeResponseDTO;
import org.example.apifluxar.dto.ProductResponseDTO;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.repository.BatchRepository;
import org.example.apifluxar.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BatchService {
    final BatchRepository batchRepository;
    final ObjectMapper objectMapper;
    final ProductService productService;
    final UnitService unitService;


    public BatchService(BatchRepository batchRepository, ObjectMapper objectMapper, ProductService productService, UnitService unitService) {
        this.batchRepository = batchRepository;
        this.objectMapper = objectMapper;
        this.productService = productService;
        this.unitService = unitService;
    }

    public BatchResposeDTO findById(Long id){
        Batch batch =  batchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(batch, BatchResposeDTO.class);

    }

    public BatchResposeDTO getBatchByIdLote(String loteId){
        Batch batch = batchRepository.findByIdLote(loteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return  objectMapper.convertValue(batch, BatchResposeDTO.class);
    }

    //restona id do produto
    public BatchResposeDTO createBatch(BatchRequestDTO batchRequestDTO){
        ProductResponseDTO produto = productService.getProductByName(batchRequestDTO.getNomeProduto());
        unitService.getUnitById(batchRequestDTO.getUnidade());
        Batch batch = objectMapper.convertValue(batchRequestDTO, Batch.class);
        batch.setProduto(produto.getId());
        batchRepository.save(batch);
        return objectMapper.convertValue(batch, BatchResposeDTO.class);
    }
}