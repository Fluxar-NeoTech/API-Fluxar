package org.example.apifluxar.service;

import org.example.apifluxar.dto.*;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {
    final EmployeeRepository employeeRepository;
    final IndustryService industryService;
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);


    public EmployeeService(EmployeeRepository employeeRepository, IndustryService industryService) {
        this.industryService = industryService;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        EmployeeResponseDTO dto = new EmployeeResponseDTO(
                employee.getId(),
                employee.getNome(),
                employee.getSobrenome(),
                employee.getEmail(),
                employee.getCargo(),
                employee.getFotoPerfil()
        );

        Sector setor = employee.getSetor();
        if (setor != null) {
            SectorResponseDTO sectorDTO = new SectorResponseDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getDescricao()
            );

            dto.setSetor(sectorDTO);
        }

        Unit unit = employee.getUnidade();
        if (unit != null) {
            UnitResponseDTO unitDTO = new UnitResponseDTO(
                    unit.getNome(),
                    unit.getCep(),
                    unit.getRua(),
                    unit.getCidade(),
                    unit.getEstado(),
                    unit.getNumero(),
                    unit.getBairro(),
                    industryService.getIndustryById(employee.getId())
            );
            dto.setUnit(unitDTO);
        }

        return dto;
    }

    public EmployeeResponseDTO login(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository
                .findByEmailAndSenha(employeeRequestDTO.getEmail(), employeeRequestDTO.getSenha())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));


        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getNome(),
                employee.getSobrenome(),
                employee.getEmail(),
                employee.getCargo(),
                employee.getFotoPerfil()
        );
    }

    public void updatePhoto(UpdatePhotoRequestDTO updatePhotoRequestDTO) {
        Employee employee = employeeRepository.findByEmail(updatePhotoRequestDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        employee.setFotoPerfil(updatePhotoRequestDTO.getFotoPerfil());
        employeeRepository.save(employee);

        log.info("Foto de perfil do funcionário ID={} | Email={} atualizada com sucesso!",
                employee.getId(), employee.getEmail());
    }
}
