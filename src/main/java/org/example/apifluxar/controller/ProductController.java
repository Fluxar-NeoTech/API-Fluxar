package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.dto.product.ProductRequestDTO;
import org.example.apifluxar.dto.product.ProductResponseDTO;
import org.example.apifluxar.openapi.ProductOpenAPI;
import org.example.apifluxar.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController implements ProductOpenAPI {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("search/all/product/by/unit/{employeeId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductRegistered(@PathVariable Long employeeId){
        List<ProductResponseDTO> productResponseDTO = productService.getAllProductRegistered(employeeId);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("search/batch/by/product/{productId}")
    public ResponseEntity<List<String>> getBatchByProduct(@PathVariable Long productId){
        List<String> batchCode = productService.getBatchByProduct(productId);
        return ResponseEntity.ok(batchCode);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        MessageResponseDTO messageResponseDTO = productService.addProduct(productRequestDTO);
        return ResponseEntity.ok(messageResponseDTO);
    }
}
