package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.products.AllProductsResponseDTO;
import org.example.apifluxar.dto.products.ProductRequestDTO;
import org.example.apifluxar.dto.products.ProductResponseDTO;
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

    @GetMapping("/search/by/id/{id}")
    @Operation(summary = "Buscar produto por ID",
            description = "Retorna os detalhes de um produto específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        ProductResponseDTO res = productService.getProductById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search/by/name/{name}")
    @Operation(summary = "Buscar produto por nome",
            description = "Retorna os detalhes de um produto específico com base no nome fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<ProductResponseDTO>> getProductByName(@PathVariable String name) {
        List<ProductResponseDTO> res = productService.getProductByName(name);
        return ResponseEntity.ok(res);
    }

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
    @Operation(summary = "Criar novo produto",
            description = "Cria um novo produto com base nas informações fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Produto criado com sucesso"),
            @ApiResponse( responseCode = "400", description = "Requisição inválida"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO res = productService.addProduct(productRequestDTO);
        return ResponseEntity.ok(res);
    }
}
