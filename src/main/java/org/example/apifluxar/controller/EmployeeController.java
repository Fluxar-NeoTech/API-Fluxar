package org.example.apifluxar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.groups.Default;
import org.example.apifluxar.dto.employee.*;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.openapi.EmployeeOpenAPI;
import org.example.apifluxar.service.EmployeeService;
import org.example.apifluxar.validation.OnCreate;
import org.example.apifluxar.validation.OnPatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<LoginEmployeeResponseDTO> login(@RequestBody @Validated({OnCreate.class, Default.class}) EmployeeRequestDTO employeeRequestDTO) {
        LoginEmployeeResponseDTO res = employeeService.login(employeeRequestDTO);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<EmployeeResponseDTO> profile(@PathVariable Long id){
        EmployeeResponseDTO res = employeeService.profile(id);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/photo")
    public ResponseEntity<MessageResponseDTO> updatePhoto(
            @RequestBody @Validated({OnPatch.class, Default.class}) UpdatePhotoRequestDTO updatePhotoRequestDTO) {
        MessageResponseDTO messageResponseDTO = employeeService.updatePhoto(updatePhotoRequestDTO);
        return ResponseEntity.ok(messageResponseDTO);
    }

    @PutMapping(value = "/update/photo/site", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponseDTO> updatePhotoSite(
            @RequestParam("email") String email,
            @RequestPart("file") MultipartFile file) {

        MessageResponseDTO response = employeeService.updatePhotoSite(email, file);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/password")
    public ResponseEntity<MessageResponseDTO> updatePassword(@RequestBody PasswordUpdateRequest request) {
        MessageResponseDTO messageResponseDTO = employeeService.updatePassword(request);
        return ResponseEntity.ok(messageResponseDTO);
    }
}
