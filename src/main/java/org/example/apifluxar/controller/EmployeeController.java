package org.example.apifluxar.controller;

import jakarta.validation.groups.Default;
import org.example.apifluxar.dto.EmployeeResponseDTO;
import org.example.apifluxar.dto.EmployeeRequestDTO;
import org.example.apifluxar.dto.UpdatePhotoRequestDTO;
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
    public ResponseEntity<EmployeeResponseDTO> selectId(@PathVariable Long id) {
        EmployeeResponseDTO res = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<EmployeeResponseDTO> login(@RequestBody @Validated({OnCreate.class, Default.class}) EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO res = employeeService.login(employeeRequestDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/photo")
    public ResponseEntity<Map<String, String>> updatePhoto(
            @RequestBody @Validated({OnPatch.class, Default.class}) UpdatePhotoRequestDTO updatePhotoRequestDTO
    ) {
        try {
            employeeService.updatePhoto(updatePhotoRequestDTO);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Foto de perfil atualizada com sucesso!");
            return ResponseEntity.ok(response);

        } catch (ResponseStatusException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Funcionário não encontrado para o email informado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erro inesperado ao atualizar a foto de perfil");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

}
