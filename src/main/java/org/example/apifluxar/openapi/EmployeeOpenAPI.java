package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.groups.Default;
import org.example.apifluxar.dto.employee.EmployeeRequestDTO;
import org.example.apifluxar.dto.employee.EmployeeResponseDTO;
import org.example.apifluxar.dto.employee.UpdatePhotoRequestDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.validation.OnCreate;
import org.example.apifluxar.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmployeeOpenAPI {

    @Operation(summary = "Login de funcionário",
            description = "Autentica um funcionário com base nas credenciais fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Funcionário autenticado com sucesso"),
            @ApiResponse( responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<EmployeeResponseDTO> login(@RequestBody @Validated({OnCreate.class, Default.class}) EmployeeRequestDTO employeeRequestDTO);

    @Operation(summary = "Atualizar foto de perfil do funcionário",
            description = "Atualiza a foto de perfil de um funcionário com base nas informações fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Foto de perfil atualizada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<MessageResponseDTO> updatePhoto(
            @RequestBody @Validated({OnPatch.class, Default.class}) UpdatePhotoRequestDTO updatePhotoRequestDTO
    );

    @Operation(summary = "Atualizar senha de perfil do funcionário",
            description = "Atualiza a senha de perfil de um funcionário com base nas informações fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<MessageResponseDTO> updateSenha(@RequestBody @Validated EmployeeRequestDTO employeeRequestDTO);
}
