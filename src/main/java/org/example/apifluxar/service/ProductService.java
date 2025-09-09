package org.example.apifluxar.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.AllProductsResponseDTO;
import org.example.apifluxar.dto.ProductRequestDTO;
import org.example.apifluxar.dto.ProductResponseDTO;
import org.example.apifluxar.dto.SectorResponseDTO;
import org.example.apifluxar.mapper.ProductMapper;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponseDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        ProductResponseDTO dto = new ProductResponseDTO(
                product.getNome(),
                product.getTipo()
        );

        Sector setor = product.getSetor();
        if (setor != null) {
            SectorResponseDTO sectorDTO = new SectorResponseDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getDescricao()
            );

            dto.setSetor(sectorDTO);
        }

        return dto;
    }

    public ProductResponseDTO getProductByName(String name) {
        Product product = productRepository.findByNome(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ProductResponseDTO dto = new ProductResponseDTO(
                product.getNome(),
                product.getTipo()
        );

        Sector setor = product.getSetor();
        if (setor != null) {
            SectorResponseDTO sectorDTO = new SectorResponseDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getDescricao()
            );

            dto.setSetor(sectorDTO);
        }

        return dto;
    }

    public List<AllProductsResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<AllProductsResponseDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            AllProductsResponseDTO dto = new AllProductsResponseDTO(
                    product.getNome(),
                    product.getTipo()
            );
            productDTOs.add(dto);
        }

        return productDTOs;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.mapToProduct(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductResponseDTO(savedProduct);
    }
}
