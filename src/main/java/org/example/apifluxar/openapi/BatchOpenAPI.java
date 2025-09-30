package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.apifluxar.dto.batch.BatchRequestDTO;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BatchOpenAPI {
    @Operation(summary = "Buscar lote por código",
            description = "Retorna os detalhes de um lote específico com base no código fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lote encontrado com sucesso")
            ,@ApiResponse( responseCode = "404", description = "Lote não encontrado")
            ,@ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<BatchResponseDTO> getByBatchCode(@PathVariable String batchCode);

    @Operation(summary = "Adicionar um novo lote",
            description = "Adiciona um novo lote ao sistema com base nos dados fornecidos.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lote adicionado com sucesso"),
            @ApiResponse( responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<BatchResponseDTO> addBatch(@RequestBody BatchRequestDTO batchRequestDTO);

    @Operation(summary = "Deletar um lote por código", description = "Deleta um lote do sistema com base no código fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Lote deletado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<BatchResponseDTO> deleteBatch(@PathVariable String batchCode);
}
