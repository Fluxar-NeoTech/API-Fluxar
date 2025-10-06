package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.dto.product.ProductRequestDTO;
import org.example.apifluxar.dto.product.ProductResponseDTO;
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

//    @GetMapping("/search/by/id/{id}")
//    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
//        ProductResponseDTO res = productService.getProductById(id);
//        return ResponseEntity.ok(res);
//    }

//    @GetMapping("/search/by/name/{name}")
//    public ResponseEntity<List<ProductResponseDTO>> getProductByName(@PathVariable String name) {
//        List<ProductResponseDTO> res = productService.getProductByName(name);
//        return ResponseEntity.ok(res);
//    }

//    @GetMapping("/search/all")
//    @Operation(summary = "Listar todos os produtos",
//            description = "Retorna uma lista de todos os produtos cadastrados no sistema.")
//    @ApiResponses({
//            @ApiResponse( responseCode = "200", description = "Lista de produtos retornada com sucesso"),
//            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
//    })
//    public ResponseEntity<List<AllProductsResponseDTO>> getAllProducts() {
//        List<AllProductsResponseDTO> res = productService.getAllProducts();
//        return ResponseEntity.ok(res);
//    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        MessageResponseDTO messageResponseDTO = productService.addProduct(productRequestDTO);
        return ResponseEntity.ok(messageResponseDTO);
    }
}
