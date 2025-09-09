package org.example.apifluxar.controller;

import org.example.apifluxar.dto.AllProductsResponseDTO;
import org.example.apifluxar.dto.ProductRequestDTO;
import org.example.apifluxar.dto.ProductResponseDTO;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        ProductResponseDTO res = productService.getProductById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<ProductResponseDTO> selectByName(@PathVariable String name) {
        ProductResponseDTO res = productService.getProductByName(name);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search/product")
    public ResponseEntity<List<AllProductsResponseDTO>> selectProduct() {
        List<AllProductsResponseDTO> res = productService.getAllProducts();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/create/product")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO res = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(res);
    }
}
