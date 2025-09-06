package org.example.apifluxar.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.ProductRequestDTO;
import org.example.apifluxar.dto.ProductResponseDTO;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;
    ObjectMapper objectMapper;

    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    public  ProductResponseDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(product, ProductResponseDTO.class);
    }

    public ProductResponseDTO getProductByName(String name) {
        Product product = productRepository.findByNome(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(product, ProductResponseDTO.class);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return objectMapper.convertValue(products, new TypeReference<List<ProductResponseDTO>>() {});
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = objectMapper.convertValue(productRequestDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return objectMapper.convertValue(savedProduct, ProductResponseDTO.class);
    }
}
