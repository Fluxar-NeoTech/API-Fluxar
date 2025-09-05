package org.example.apifluxar.controller;

import jakarta.validation.Valid;
import org.example.apifluxar.dto.EmployeeResponseDTO;
import org.example.apifluxar.dto.EmployeeRequestDTO;
import org.example.apifluxar.service.EmployeeService;
import org.example.apifluxar.validation.OnCreate;
import org.example.apifluxar.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search/email/{email}")
    public ResponseEntity<EmployeeResponseDTO> selectEmail(@PathVariable String email) {
        EmployeeResponseDTO res = employeeService.getEmployeeByEmail(email);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<EmployeeResponseDTO> login(@RequestBody @Validated({OnCreate.class}) EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO res = employeeService.login(employeeRequestDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updade/photo")
    public ResponseEntity<EmployeeResponseDTO> updadePhoto(@RequestBody @Validated({OnPatch.class}) EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO res = employeeService.updadePhoto(employeeRequestDTO);
        return ResponseEntity.ok(res);
    }
}
