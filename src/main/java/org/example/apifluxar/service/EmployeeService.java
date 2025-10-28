package org.example.apifluxar.service;

import org.example.apifluxar.dto.employee.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.example.apifluxar.dto.capacityStock.CapacityStockResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.example.apifluxar.dto.cloud.CloudinayUploadResponse;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.dto.plan.PlanResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.exception.NotAuthorizedEmployee;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.projection.PlanProjection;
import org.example.apifluxar.repository.EmployeeRepository;
import org.example.apifluxar.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
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
    final DailyActiveUsersService dailyActiveUsersService;
    final JwtService jwtService;
    final Argon2PasswordEncoder passwordEncoder;



    public EmployeeService(EmployeeRepository employeeRepository,
                           IndustryService industryService,
                           CapacityStockService capacityStockService,
                           SectorService sectorService,
                           UnitService unitService,
                           CloudinaryService cloudinaryService,
                           DailyActiveUsersService dailyActiveUsersService,
                           JwtService jwtService,
                           Argon2PasswordEncoder passwordEncoder) {
        this.unitService = unitService;
        this.sectorService = sectorService;
        this.industryService = industryService;
        this.employeeRepository = employeeRepository;
        this.capacityStockService = capacityStockService;
        this.cloudinaryService =cloudinaryService;
        this.dailyActiveUsersService = dailyActiveUsersService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginEmployeeResponseDTO login(EmployeeRequestDTO employeeRequestDTO) {
     Employee employee = employeeRepository
                .findByEmail(employeeRequestDTO.getEmail().toLowerCase())
                .orElseThrow(() -> new NotAuthorizedEmployee(
                        "Você não está autorizado a acessar o sistema. " +
                        "Verifique suas credenciais e tente novamente."));

        boolean comparePassword = passwordEncoder.matches(
                employeeRequestDTO.getPassword(),
                employee.getPassword()
        );

        if (!comparePassword) {
            throw new NotAuthorizedEmployee(
                    "Você não está autorizado a acessar o sistema. " +
                    "Verifique suas credenciais e tente novamente.");
        }

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(employee.getEmail(), null);

        String token = jwtService.generateToken(authentication);

        dailyActiveUsersService.insertAccess(employee.getId(), employeeRequestDTO.getOrigin());

        return new LoginEmployeeResponseDTO(
                employee.getId(),
                employee.getRole(),
                employee.getEmail(),
                token
        );

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

    public MessageResponseDTO updatePassword(PasswordUpdateRequest request) {
        Employee employee = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Funcionário não encontrado para o email: %s", request.getEmail())
                ));

        // Hash da nova senha
        String hashedPassword = passwordEncoder.encode(request.getNewPassword());
        employee.setPassword(hashedPassword);

        employeeRepository.save(employee);

        log.info("Senha atualizada com sucesso para o funcionário (ID={}, Email={})",
                employee.getId(), employee.getEmail());

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
                    planProjection.getPlanName(),
                    planProjection.getMonthsDuration()
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

    public EmployeeResponseDTO findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado"));
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
                    planProjection.getPlanName(),
                    planProjection.getMonthsDuration()
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
