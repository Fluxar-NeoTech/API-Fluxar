package org.example.apifluxar.service;

import org.example.apifluxar.dto.capacityStock.CapacityStockResposeDTO;
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
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);


    public EmployeeService(EmployeeRepository employeeRepository, IndustryService industryService, CapacityStockService capacityStockService) {
        this.industryService = industryService;
        this.employeeRepository = employeeRepository;
        this.capacityStockService = capacityStockService;
    }

    public EmployeeResponseDTO login(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository
                .findByEmailAndSenha(employeeRequestDTO.getEmail(), employeeRequestDTO.getSenha())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));


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
                    unit.getId(),
                    unit.getNome(),
                    unit.getCep(),
                    unit.getRua(),
                    unit.getCidade(),
                    unit.getEstado(),
                    unit.getNumero(),
                    unit.getBairro(),
                    industryService.getIndustryById(employee.getUnidade().getIndustry().getId())
            );
            unitDTO.setId(unit.getId());
            dto.setUnit(unitDTO);
        }
        CapacityStockResposeDTO capacityStockResposeDTO = capacityStockService.findByUnidadeIdAndSectorId(unit.getId(), setor.getId());
        if (capacityStockResposeDTO != null) {
            Double capacidadeMaxima= capacityStockResposeDTO.getCapacidadeMaxima();
            dto.setCapacidadeMaxima(capacidadeMaxima);
        }


        return dto;
    }

    public MessageResponseDTO updatePhoto(UpdatePhotoRequestDTO updatePhotoRequestDTO) {
        Employee employee = employeeRepository.findByEmail(updatePhotoRequestDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado para o email informado"));

        employee.setFotoPerfil(updatePhotoRequestDTO.getFotoPerfil());
        employeeRepository.save(employee);

        log.info("Foto de perfil do funcionário ID={} | Email={} atualizada com sucesso!", employee.getId(), employee.getEmail());

        return new MessageResponseDTO("Foto de perfil atualizada com sucesso!");
    }

    public MessageResponseDTO updateSenha(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findByEmail(employeeRequestDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado para o email informado"));

        employee.setSenha(employeeRequestDTO.getSenha());
        employeeRepository.save(employee);

        log.info("Senha do funcionário ID={} | Email={} atualizada com sucesso!", employee.getId(), employee.getEmail());

        return new MessageResponseDTO("Senha atualizada com sucesso!");
    }

}
