package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.products.AllProductsResponseDTO;
import org.example.apifluxar.dto.products.ProductRequestDTO;
import org.example.apifluxar.dto.products.ProductResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
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

    public ProductService(ProductRepository productRepository, ProductMapper productMapper,SectorRepository sectorRepository,ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.sectorRepository = sectorRepository;
        this.objectMapper = objectMapper;
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
        Sector setor = sectorRepository.findById(productRequestDTO.getSetor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));

        Product product = productMapper.mapToProduct(productRequestDTO, setor);
        Product savedProduct = productRepository.save(product);

        return productMapper.mapToProductResponseDTO(savedProduct);
    }

}
