package org.example.apifluxar.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.groups.Default;
import org.example.apifluxar.dto.employee.*;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.security.SecurityConfig;
import org.example.apifluxar.validation.OnCreate;
import org.example.apifluxar.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@SecurityRequirement(name = SecurityConfig.SECURITY)
public interface EmployeeOpenAPI {

    @Operation(summary = "Login de funcionário",
            description = "Autentica um funcionário com base nas credenciais fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Funcionário autenticado com sucesso"),
            @ApiResponse( responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<LoginEmployeeResponseDTO> login(@RequestBody @Validated({OnCreate.class, Default.class}) EmployeeRequestDTO employeeRequestDTO);

    @Operation(summary = "Perfil do funcionário",
            description = "Retorna os detalhes do perfil de um funcionário específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Perfil do funcionário retornado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<EmployeeResponseDTO> profile(@PathVariable Long id);

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
    ResponseEntity<MessageResponseDTO> updatePassword(@RequestBody PasswordUpdateRequest request);

    @Operation(summary = "Atualizar foto de perfil do funcionário via site",
            description = "Atualiza a foto de perfil de um funcionário com base nas informações fornecidas via site.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Foto de perfil atualizada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<MessageResponseDTO> updatePhotoSite(@RequestParam("email") String email,
            @RequestPart("file") MultipartFile file);
}
