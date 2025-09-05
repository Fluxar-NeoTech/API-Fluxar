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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<BatchResposeDTO> getAllLotesAndProduct(){
         List<ProductResponseDTO> products = productService.getAllProducts();
         List<Batch>  batches = batchRepository.findAll();
         List<BatchResposeDTO> batchesDTO =new ArrayList<>();

        for (Batch batch : batches) {
            BatchResposeDTO dto = new BatchResposeDTO();
            dto.setIdLote(batch.getId().toString());
            dto.setValidade(batch.getValidade());
            dto.setAltura(batch.getAltura());
            dto.setComprimento(batch.getComprimento());
            dto.setLargura(batch.getLargura());

            Optional<ProductResponseDTO> product = products.stream()
                    .filter(p -> p.getId().equals(batch.getProduto()))
                    .findFirst();

            if (product.isPresent()) {
                dto.setProduto(product.get().getId());
                dto.setNomeProduto(product.get().getNome());
            }

            batchesDTO.add(dto);
        }
        return batchesDTO;
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

    public BatchResposeDTO deleteBatch(Long id){
        Batch batch = batchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        batchRepository.delete(batch);
        return objectMapper.convertValue(batch, BatchResposeDTO.class);
    }
}