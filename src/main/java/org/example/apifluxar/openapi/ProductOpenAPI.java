package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.dto.product.ProductRequestDTO;
import org.example.apifluxar.dto.product.ProductResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductOpenAPI {

    @Operation(summary = "Busca todos os produtos registrados na unidade do gestor",
    description = "Retorna uma lista de todos os produtos registrados na unidade associada ao gestor especificado pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<ProductResponseDTO>> getAllProductRegistered(@PathVariable Long employeeId);

    @Operation(summary = "Busca lotes por ID do produto",
            description = "Retorna uma lista de skus associados ao produto especificado pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lotes encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<String>> getBatchByProduct(@PathVariable Long productId);

    @Operation(summary = "Criar novo produto",
            description = "Cria um novo produto com base nas informações fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Produto criado com sucesso"),
            @ApiResponse( responseCode = "400", description = "Requisição inválida"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<MessageResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO);
}
