package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.EmployeeResponseDTO;
import org.example.apifluxar.dto.EmployeeRequestDTO;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {
    final EmployeeRepository employeeRepository;
    final ObjectMapper objectMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
       Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
       return objectMapper.convertValue(employee, EmployeeResponseDTO.class);

    }

    public EmployeeResponseDTO getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(employee, EmployeeResponseDTO.class);
    }

    public EmployeeResponseDTO login(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = objectMapper.convertValue(employeeRequestDTO, Employee.class);
        employee = employeeRepository
                .findByEmailAndSenha(employee.getEmail(), employee.getSenha())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return objectMapper.convertValue(employee, EmployeeResponseDTO.class);
    }
}
