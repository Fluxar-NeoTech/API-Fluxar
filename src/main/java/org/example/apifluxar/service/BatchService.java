package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.dto.batch.BatchRequestDTO;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
import org.example.apifluxar.dto.batch.ProductBatchResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.exception.EmptyProducts;
import org.example.apifluxar.mapper.BatchMapper;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.repository.BatchRepository;
import org.example.apifluxar.repository.ProductRepository;
import org.example.apifluxar.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService {
    final BatchRepository batchRepository;
    final ProductService productService;
    final UnitService unitService;
    final BatchMapper batchMapper;
    final StockHistoryService stockHistoryService;
    final ProductRepository productRepository;
    final UnitRepository unitRepository;
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

//    public BatchResponseDTO getBatchByCode(String batchCode) {
//        Batch batch = batchRepository.findByBatchCode(batchCode).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote não encontrado"));
//        Product product = batch.getProduct();
//
//        BatchResponseDTO dto = new BatchResponseDTO(
//                batch.getBatchCode(),
//                batch.getExpirationDate(),
//                batch.getHeight(),
//                batch.getLength(),
//                batch.getWidth(),
//                batch.getVolume(),
//                productService.getProductById(product.getId())
//        );

//        Product product = batch.getProduct();
//        if (product != null) {
//            ProductResponseDTO productResponseDTO = productService.getProductById(product.getId());
//            dto.setProduct(productResponseDTO);
//        }

//        Unit unit = batch.getUnit();
//        if (unit != null) {
//            UnitResponseDTO unitResponseDTO = unitService.getUnitById(unit.getId());
//            dto.setUnit(unitResponseDTO);
//        }
//
//        return dto;
//    }


//    public List<BatchResponseDTO> getAllBatchByUnit(Long idUnit){
//        List<Batch> batchs = batchRepository.findAllByUnidade(idUnit);
//        List<BatchResponseDTO> batchDtos = new ArrayList<>();
//
//        for (Batch batch : batchs) {
//            BatchResponseDTO dto = new BatchResponseDTO(
//                    batch.getBatchCode(),
//                    batch.getExpirationDate(),
//                    batch.getHeight(),
//                    batch.getLength(),
//                    batch.getWidth(),
//                    batch.getVolume()
//            );
//
//            Product product = batch.getProduct();
//            if (product != null) {
//                ProductResponseDTO productResponseDTO = productService.getProductById(product.getId());
//                dto.setProduct(productResponseDTO);
//            }
//
//            Unit unit = batch.getUnit();
//            if (unit != null) {
//                UnitResponseDTO unitResponseDTO = unitService.getUnitById(unit.getId());
//                dto.setUnit(unitResponseDTO);
//            }
//
//            batchDtos.add(dto);
//        }
//
//        return batchDtos;
//    }


    public List<ProductBatchResponseDTO> getAllProductBatch(Long unitId, Long sectorId) {
        List<Batch> productBatchList = batchRepository.findAllBatchesInUnitAndSector(unitId, sectorId);
        List<ProductBatchResponseDTO> batchResponseDTO = new ArrayList<>();

        for (Batch batch : productBatchList) {
            ProductBatchResponseDTO dto = new ProductBatchResponseDTO(
                    batch.getBatchCode(),
                    batch.getExpirationDate(),
                    batch.getHeight(),
                    batch.getLength(),
                    batch.getWidth(),
                    batch.getProduct().getName()
            );

            batchResponseDTO.add(dto);
        }

        if (batchResponseDTO.isEmpty()){
            throw new EmptyProducts("Não existem produtos cadastrados");
        }

        return batchResponseDTO;
    }


    public MessageResponseDTO addBatch(BatchRequestDTO batchRequestDTO) {

        Product productEntity = productRepository.findById(batchRequestDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        Unit unitEntity = unitRepository.findById(batchRequestDTO.getUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));


        batchRepository.adicionarLote(
                batchRequestDTO.getBatchCode(),
                java.sql.Timestamp.valueOf(batchRequestDTO.getExpirationDate().atStartOfDay()),
                batchRequestDTO.getHeight(),
                batchRequestDTO.getLength(),
                batchRequestDTO.getWidth(),
                productEntity.getName(),
                productEntity.getType(),
                unitEntity.getId(),
                productEntity.getSector().getId()
        );

        return new MessageResponseDTO("Lote inserido com sucesso!");
    }

    public MessageResponseDTO deleteBatch(String batchCode) {
        Batch batch = batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new EntityNotFoundException("Lote não encontrado"));

        Product productEntity = productRepository.findById(batch.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        batchRepository.deleteBatch(batchCode);

        return new MessageResponseDTO("Lote removido com sucesso!");
    }
}
