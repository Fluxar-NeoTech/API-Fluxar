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
    final SectorService sectorService;
    final BatchService batchService;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper,SectorRepository sectorRepository,ObjectMapper objectMapper, SectorService sectorService, BatchService batchService) {
        this.sectorService = sectorService;
        this.batchService = batchService;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.sectorRepository = sectorRepository;
        this.objectMapper = objectMapper;
    }

    public ProductResponseDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        ProductResponseDTO dto = new ProductResponseDTO(
                product.getName(),
                product.getType()
        );

        Sector setor = product.getSector();
        if (setor != null) {
            SectorResponseDTO sectorResponseDTO = sectorService.getSectorById(setor.getId());
            dto.setSetor(sectorResponseDTO);
        }

        return dto;
    }

    public List<ProductResponseDTO> getProductByName(String name) {
        List<Product> product = productRepository.findByNome(name);
        List<ProductResponseDTO> dtos = new ArrayList<>();

        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        for (Product p : product) {
            ProductResponseDTO dto = new ProductResponseDTO(
                    p.getName(),
                    p.getType()
            );

            Sector setor = p.getSector();
            if (setor != null) {
                SectorResponseDTO sectorResponseDTO = sectorService.getSectorById(setor.getId());
                dto.setSetor(sectorResponseDTO);
            }
            dtos.add(dto);
        }

        return dtos;
    }

    public List<AllProductsResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<AllProductsResponseDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            AllProductsResponseDTO dto = new AllProductsResponseDTO(
                    product.getName(),
                    product.getType()
            );
            productDTOs.add(dto);
        }

        return productDTOs;
    }

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Sector setor = sectorRepository.findById(productRequestDTO.getSetor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));

        Product product = productMapper.mapToProduct(productRequestDTO, setor);
        Product savedProduct = productRepository.save(product);

        return productMapper.mapToProductResponseDTO(savedProduct);
    }

}
