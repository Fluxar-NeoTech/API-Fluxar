package org.example.apifluxar.service;

import org.example.apifluxar.dto.capacityStock.CapacityStockResponseDTO;
import org.example.apifluxar.dto.employee.EmployeeRequestDTO;
import org.example.apifluxar.dto.employee.EmployeeResponseDTO;
import org.example.apifluxar.dto.employee.UpdatePhotoRequestDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
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
    final CapacityStockService capacityStockService;
    final SectorService sectorService;
    final UnitService unitService;
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);


    public EmployeeService(EmployeeRepository employeeRepository, IndustryService industryService, CapacityStockService capacityStockService, SectorService sectorService, UnitService unitService) {
        this.unitService = unitService;
        this.sectorService = sectorService;
        this.industryService = industryService;
        this.employeeRepository = employeeRepository;
        this.capacityStockService = capacityStockService;
    }

    public EmployeeResponseDTO login(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository
                .findByEmailAndSenha(employeeRequestDTO.getEmail(), employeeRequestDTO.getPassword())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));


        EmployeeResponseDTO dto = new EmployeeResponseDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getRole(),
                employee.getProfilePicture()
        );

        Sector setor = employee.getSector();
        if (setor != null) {
            SectorResponseDTO sectorResponseDTO = sectorService.getSectorById(setor.getId());
            dto.setSector(sectorResponseDTO);
        }

        Unit unit = employee.getUnit();
        if (unit != null) {
            UnitResponseDTO unitResponseDTO = unitService.getUnitById(unit.getId());
            dto.setUnit(unitResponseDTO);
        }

        CapacityStockResponseDTO capacityStockResposeDTO = capacityStockService.getByUnitAndSector(unit.getId(), setor.getId());
        if (capacityStockResposeDTO != null) {
            Double capacidadeMaxima= capacityStockResposeDTO.getMaxCapacity();
            dto.setMaxCapacity(capacidadeMaxima);
        }
        return dto;
    }

    public MessageResponseDTO updatePhoto(UpdatePhotoRequestDTO updatePhotoRequestDTO) {
        Employee employee = employeeRepository.findByEmail(updatePhotoRequestDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado para o email informado"));

        employee.setProfilePicture(updatePhotoRequestDTO.getProfilePhoto());
        employeeRepository.save(employee);

        log.info("Foto de perfil do funcionário ID={} | Email={} atualizada com sucesso!", employee.getId(), employee.getEmail());

        return new MessageResponseDTO("Foto de perfil atualizada com sucesso!");
    }

    public MessageResponseDTO updateSenha(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findByEmail(employeeRequestDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado para o email informado"));

        employee.setPassword(employeeRequestDTO.getPassword());
        employeeRepository.save(employee);

        log.info("Senha do funcionário ID={} | Email={} atualizada com sucesso!", employee.getId(), employee.getEmail());

        return new MessageResponseDTO("Senha atualizada com sucesso!");
    }

}
