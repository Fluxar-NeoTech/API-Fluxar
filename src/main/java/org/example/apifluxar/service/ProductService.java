package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.dto.product.ProductRequestDTO;
import org.example.apifluxar.dto.product.ProductResponseDTO;
import org.example.apifluxar.exception.EmptyProducts;
import org.example.apifluxar.mapper.ProductMapper;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.repository.ProductRepository;
import org.example.apifluxar.repository.SectorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;
    final ObjectMapper objectMapper;
    final SectorRepository sectorRepository;
    final SectorService sectorService;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper,SectorRepository sectorRepository,ObjectMapper objectMapper, SectorService sectorService) {
        this.sectorService = sectorService;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.sectorRepository = sectorRepository;
        this.objectMapper = objectMapper;
    }

    public List<ProductResponseDTO> getAllProductRegistered(Long employeeId) {
        List<Product> products = productRepository.findAllProductRegistered(employeeId);

        List<ProductResponseDTO> productResponse = new ArrayList<>();
        for (Product product : products) {
            productResponse.add(new ProductResponseDTO(product.getId(), product.getName(), product.getType()));
        }

        if (productResponse.isEmpty()) {
            throw new EmptyProducts("Não existem produtos cadastrados");
        }

        return productResponse;
    }

    public List<String> getBatchByProduct(Long productId){
        return productRepository.findBatchByProduct(productId);
    }

//    public ProductResponseDTO getProductById(Long id){
//        Product product = productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Produto não encontrado"));
//
//        ProductResponseDTO dto = new ProductResponseDTO(
//                product.getName(),
//                product.getType()
//        );
//
//        Sector setor = product.getSector();
//        if (setor != null) {
//            SectorResponseDTO sectorResponseDTO = sectorService.getSectorById(setor.getId());
//            dto.setSector(sectorResponseDTO);
//        }
//
//        return dto;
//    }

//    // Retorna apenas nome e tipo do produto - usado no Mapper de Lote
//    public AllProductsResponseDTO getAllProductById(Long id){
//        Product product = productRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        AllProductsResponseDTO dto = new AllProductsResponseDTO(
//                product.getName(),
//                product.getType()
//        );
//        return dto;
//    }

//    public List<ProductResponseDTO> getProductByName(String name) {
//        List<Product> product = productRepository.findByName(name);
//        List<ProductResponseDTO> dtos = new ArrayList<>();
//
//        if (product.isEmpty()) {
//            throw new EntityNotFoundException("Produto não encontrado");
//        }
//
//        for (Product p : product) {
//            ProductResponseDTO dto = new ProductResponseDTO(
//                    p.getName(),
//                    p.getType()
//            );
//
//            Sector setor = p.getSector();
//            if (setor != null) {
//                SectorResponseDTO sectorResponseDTO = sectorService.getSectorById(setor.getId());
//                dto.setSector(sectorResponseDTO);
//            }
//            dtos.add(dto);
//        }
//
//        return dtos;
//    }

//    public List<AllProductsResponseDTO> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        List<AllProductsResponseDTO> productDTOs = new ArrayList<>();
//
//        for (Product product : products) {
//            AllProductsResponseDTO dto = new AllProductsResponseDTO(
//                    product.getName(),
//                    product.getType()
//            );
//            productDTOs.add(dto);
//        }
//
//        return productDTOs;
//    }

    public MessageResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Sector setor = sectorRepository.findById(productRequestDTO.getSectorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));

        Product product = productMapper.mapToProduct(productRequestDTO, setor);
        productRepository.save(product);

        return new MessageResponseDTO("Produto inserido com sucesso!");
    }
}
