package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.batch.BatchRequestDTO;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
import org.example.apifluxar.dto.batch.ProductBatchResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BatchOpenAPI {
    @Operation(summary = "Listar todos os lotes por ID da unidade e setor",
            description = "Retorna uma lista de todos os lotes cadastrados no sistema filtrando pelo ID da unidade e setor.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lista de lotes retornada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Unidade ou setor não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<ProductBatchResponseDTO>>
    getAllBatchByUnitAndSector(@RequestParam Long unitId, @RequestParam Long sectorId);

    @Operation(summary = "Adicionar um novo lote",
            description = "Adiciona um novo lote ao sistema com base nas informações fornecidas no corpo da requisição.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lote adicionado com sucesso"),
            @ApiResponse( responseCode = "400", description = "Requisição inválida"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<MessageResponseDTO> addBatch(@RequestBody BatchRequestDTO batchRequestDTO);

    @Operation(summary = "Deletar um lote por código",
            description = "Deleta um lote do sistema com base no código fornecido na URL.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lote deletado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<MessageResponseDTO> deleteBatch(@PathVariable String batchCode);
}
