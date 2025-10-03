package org.example.apifluxar.service;

import org.example.apifluxar.dto.capacityStock.CapacityStockResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.dto.cloud.CloudinayUploadResponse;
import org.example.apifluxar.dto.employee.EmployeeRequestDTO;
import org.example.apifluxar.dto.employee.EmployeeResponseDTO;
import org.example.apifluxar.dto.employee.LoginEmployeeResponseDTO;
import org.example.apifluxar.dto.employee.UpdatePhotoRequestDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.dto.plan.PlanResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.exception.NotAuthorizedEmployee;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Plan;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.projection.PlanProjection;
import org.example.apifluxar.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {
    final EmployeeRepository employeeRepository;
    final IndustryService industryService;
    final CapacityStockService capacityStockService;
    final SectorService sectorService;
    final UnitService unitService;
    final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    final CloudinaryService cloudinaryService;


    public EmployeeService(EmployeeRepository employeeRepository, IndustryService industryService, CapacityStockService capacityStockService, SectorService sectorService, UnitService unitService,CloudinaryService cloudinaryService) {
        this.unitService = unitService;
        this.sectorService = sectorService;
        this.industryService = industryService;
        this.employeeRepository = employeeRepository;
        this.capacityStockService = capacityStockService;
        this.cloudinaryService =cloudinaryService;
    }

    public LoginEmployeeResponseDTO login(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository
                .findByEmailAndPassword(employeeRequestDTO.getEmail(), employeeRequestDTO.getPassword())
                .orElseThrow(() -> new NotAuthorizedEmployee("Você não está autorizado a acessar o sistema. " +
                        "Verifique suas credenciais e tente novamente."));


        LoginEmployeeResponseDTO dto = new LoginEmployeeResponseDTO(
                employee.getId(),
                employee.getRole(),
                employee.getEmail()
        );

        return dto;
    }

    public MessageResponseDTO updatePhoto(UpdatePhotoRequestDTO updatePhotoRequestDTO) {
        Employee employee = employeeRepository.findByEmail(updatePhotoRequestDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado para o email informado"));

        employee.setProfilePicture(updatePhotoRequestDTO.getProfilePhoto());
        employeeRepository.save(employee);

        log.info("Foto de perfil do funcionário ID={} | Email={} atualizada com sucesso!", employee.getId(), employee.getEmail());

        return new MessageResponseDTO("Foto de perfil atualizada com sucesso!");
    }

    public MessageResponseDTO updatePhotoSite(String email, MultipartFile file) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado para o email informado"));

        // Faz upload no Cloudinary
        CloudinayUploadResponse uploadResponse = cloudinaryService.upload(file, "user_profile_photos");

        // Atualiza no banco a URL retornada
        employee.setProfilePicture(uploadResponse.getUrl());
        employeeRepository.save(employee);

        log.info("Foto de perfil do funcionário ID={} | Email={} atualizada com sucesso! (via site)",
                employee.getId(), employee.getEmail());

        return new MessageResponseDTO("Foto de perfil atualizada com sucesso!");
    }



    public MessageResponseDTO updateSenha(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findByEmail(employeeRequestDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado para o email informado"));

        employee.setPassword(employeeRequestDTO.getPassword());
        employeeRepository.save(employee);

        log.info("Senha do funcionário ID={} | Email={} atualizada com sucesso!", employee.getId(), employee.getEmail());

        return new MessageResponseDTO("Senha atualizada com sucesso!");
    }

    public EmployeeResponseDTO profile(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado"));
        EmployeeResponseDTO dto = new EmployeeResponseDTO(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
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

        PlanProjection planProjection = employeeRepository.findByIndustryPlan(employee.getId(), employee.getUnit().getIndustry().getId());
        if (planProjection != null) {
            PlanResponseDTO planDto = new PlanResponseDTO(
                    planProjection.getNomeDoPlano(),
                    planProjection.getDuracaoMeses()
            );
            dto.setPlan(planDto);
        }

        CapacityStockResponseDTO capacityStockResponseDTO = capacityStockService.getByUnitAndSector(unit.getId(), setor.getId());
        if (capacityStockResponseDTO != null) {
            Double capacidadeMaxima= capacityStockResponseDTO.getMaxCapacity();
            dto.setMaxCapacity(capacidadeMaxima);
        }
        return dto;
    }

}
