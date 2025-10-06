package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.product.ProductRequestDTO;
import org.example.apifluxar.dto.product.ProductResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductOpenAPI {

    @Operation(summary = "Busca todos os produtos registrados na unidade do gestor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Indústria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<ProductResponseDTO>> getAllProductRegistered(@PathVariable Long employeeId);
//    @Operation(summary = "Buscar produto por ID",
//            description = "Retorna os detalhes de um produto específico com base no ID fornecido.")
//    @ApiResponses({
//            @ApiResponse( responseCode = "200", description = "Produto encontrado com sucesso"),
//            @ApiResponse( responseCode = "404", description = "Produto não encontrado"),
//            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
//    })
//    ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id);

//    @Operation(summary = "Buscar produto por nome",
//            description = "Retorna os detalhes de um produto específico com base no nome fornecido.")
//    @ApiResponses({
//            @ApiResponse( responseCode = "200", description = "Produto encontrado com sucesso"),
//            @ApiResponse( responseCode = "404", description = "Produto não encontrado"),
//            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
//    })
//    ResponseEntity<List<ProductResponseDTO>> getProductByName(@PathVariable String name);

    @Operation(summary = "Criar novo produto",
            description = "Cria um novo produto com base nas informações fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Produto criado com sucesso"),
            @ApiResponse( responseCode = "400", description = "Requisição inválida"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO);
}
