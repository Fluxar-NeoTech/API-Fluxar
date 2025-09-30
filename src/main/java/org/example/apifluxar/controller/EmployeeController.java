package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.groups.Default;
import org.example.apifluxar.dto.employee.EmployeeResponseDTO;
import org.example.apifluxar.dto.employee.EmployeeRequestDTO;
import org.example.apifluxar.dto.employee.UpdatePhotoRequestDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.openapi.EmployeeOpenAPI;
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
public class EmployeeController implements EmployeeOpenAPI {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public ResponseEntity<EmployeeResponseDTO> login(@RequestBody @Validated({OnCreate.class, Default.class}) EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO res = employeeService.login(employeeRequestDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/photo")
    public ResponseEntity<MessageResponseDTO> updatePhoto(
            @RequestBody @Validated({OnPatch.class, Default.class}) UpdatePhotoRequestDTO updatePhotoRequestDTO) {
        MessageResponseDTO response = employeeService.updatePhoto(updatePhotoRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/senha")
    public ResponseEntity<MessageResponseDTO> updateSenha( @RequestBody @Validated EmployeeRequestDTO employeeRequestDTO) {
        MessageResponseDTO response = employeeService.updateSenha(employeeRequestDTO);
        return ResponseEntity.ok(response);
    }

}
