package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.groups.Default;
import org.example.apifluxar.dto.employee.EmployeeResponseDTO;
import org.example.apifluxar.dto.employee.EmployeeRequestDTO;
import org.example.apifluxar.dto.employee.UpdatePhotoRequestDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.service.EmployeeService;
import org.example.apifluxar.validation.OnCreate;
import org.example.apifluxar.validation.OnPatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "Buscar funcionário por ID",
            description = "Retorna os detalhes de um funcionário específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Funcionário encontrado com sucesso"),
            @ApiResponse( responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EmployeeResponseDTO> selectId(@PathVariable Long id) {
        EmployeeResponseDTO res = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    @Operation(summary = "Login de funcionário",
            description = "Autentica um funcionário com base nas credenciais fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Funcionário autenticado com sucesso"),
            @ApiResponse( responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EmployeeResponseDTO> login(@RequestBody @Validated({OnCreate.class, Default.class}) EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO res = employeeService.login(employeeRequestDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/photo")
    @Operation(summary = "Atualizar foto de perfil do funcionário",
            description = "Atualiza a foto de perfil de um funcionário com base nas informações fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Foto de perfil atualizada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<MessageResponseDTO> updatePhoto(
            @RequestBody @Validated({OnPatch.class, Default.class}) UpdatePhotoRequestDTO updatePhotoRequestDTO
    ) {
        MessageResponseDTO response = employeeService.updatePhoto(updatePhotoRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/senha")
    @Operation(summary = "Atualizar senha de perfil do funcionário",
            description = "Atualiza a senha de perfil de um funcionário com base nas informações fornecidas.")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<MessageResponseDTO> updateSenha(
            @RequestBody @Validated EmployeeRequestDTO employeeRequestDTO
    ) {
        MessageResponseDTO response = employeeService.updateSenha(employeeRequestDTO);
        return ResponseEntity.ok(response);
    }

}
