package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.*;
import org.example.apifluxar.mapper.BatchMapper;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.repository.BatchRepository;
import org.example.apifluxar.repository.ProductRepository;
import org.example.apifluxar.repository.StockHistoryRepository;
import org.example.apifluxar.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BatchService {
    final BatchRepository batchRepository;
    final ProductService productService;
    final UnitService unitService;
    final BatchMapper batchMapper;
    final StockHistoryService stockHistoryService;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    final ObjectMapper objectMapper;

    public BatchService(BatchRepository batchRepository,
                        ProductService productService,
                        UnitService unitService,
                        BatchMapper batchMapper,
                        ProductRepository productRepository,
                        UnitRepository unitRepository,
                        StockHistoryService stockHistoryService,
                        ObjectMapper objectMapper
    ) {
        this.batchRepository = batchRepository;
        this.productService = productService;
        this.unitService = unitService;
        this.batchMapper = batchMapper;
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
        this.stockHistoryService = stockHistoryService;
        this.objectMapper = objectMapper;
    }

    public BatchResponseDTO getBatchByIdLote(String loteId){
        Batch batch = batchRepository.findByIdLote(loteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        BatchResponseDTO dto = new BatchResponseDTO(
                batch.getIdLote(),
                batch.getValidade(),
                batch.getAltura(),
                batch.getComprimento(),
                batch.getLargura(),
                batch.getVolume()
        );

        Product product = batch.getProduto();

        if (product != null) {
            ProductResponseDTO productResponseDTO = productService.getProductById(product.getId());
            dto.setProduct(productResponseDTO);
        }

        return dto;
    }

    public List<BatchResponseDTO> getAllBatch(){
        List<Batch> batchs = batchRepository.findAll();
        List<BatchResponseDTO> batchDtos = new ArrayList<>();

        for (Batch batch : batchs) {
            BatchResponseDTO dto = new BatchResponseDTO(
                    batch.getIdLote(),
                    batch.getValidade(),
                    batch.getAltura(),
                    batch.getComprimento(),
                    batch.getLargura(),
                    batch.getVolume()
            );

            Product product = batch.getProduto();
            if (product != null) {
                ProductResponseDTO productResponseDTO = productService.getProductById(product.getId());
                dto.setProduct(productResponseDTO);
            }


            Unit unit = batch.getUnidade();
            if (unit != null) {
                UnitResponseDTO unitResponseDTO = unitService.getUnitById(unit.getId());
                dto.setUnit(unitResponseDTO);
            }
            batchDtos.add(dto);
        }

        return batchDtos;
    }

    public List<BatchResponseDTO> getAllBatchByUnit(Long idUnit){
        List<Batch> batchs = batchRepository.findAllByUnidade(idUnit);
        List<BatchResponseDTO> batchDtos = new ArrayList<>();

        for (Batch batch : batchs) {
            BatchResponseDTO dto = new BatchResponseDTO(
                    batch.getIdLote(),
                    batch.getValidade(),
                    batch.getAltura(),
                    batch.getComprimento(),
                    batch.getLargura(),
                    batch.getVolume()
            );

            Product product = batch.getProduto();
            if (product != null) {
                ProductResponseDTO productResponseDTO = productService.getProductById(product.getId());
                dto.setProduct(productResponseDTO);
            }

            Unit unit = batch.getUnidade();
            if (unit != null) {
                UnitResponseDTO unitResponseDTO = unitService.getUnitById(unit.getId());
                dto.setUnit(unitResponseDTO);
            }

            batchDtos.add(dto);
        }

        return batchDtos;
    }

    public BatchResponseCreateDTO createBatch(BatchRequestDTO batchRequestDTO) {
        Product productEntity = productRepository.findById(batchRequestDTO.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Unit unitEntity = unitRepository.findById(batchRequestDTO.getUnitId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrada"));

        Batch batchEntity = batchMapper.mapToBatch(batchRequestDTO, productEntity, unitEntity);

        Batch savedBatch = batchRepository.save(batchEntity);

        return batchMapper.mapToBatchCreate(savedBatch);
    }


    public BatchResponseDTO deleteBatch(String id){
        Batch batch = batchRepository.findByIdLote(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        stockHistoryService.deleteByBatchId(batch.getId());
        batchRepository.deleteByIdCustom(batch.getId());
        BatchResponseDTO res = batchMapper.mapToBatch(batch,batch.getProduto(),batch.getUnidade());
        return res;
    }
}
