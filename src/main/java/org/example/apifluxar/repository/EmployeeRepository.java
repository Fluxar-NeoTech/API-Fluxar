package org.example.apifluxar.repository;

import org.example.apifluxar.dto.plan.PlanResponseDTO;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Plan;
import org.example.apifluxar.projection.PlanProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailAndPassword(String email, String password);
    Optional<Employee> findByEmail(String email);

    @Query(value = "SELECT nome_do_plano, duracao_meses " +
            "FROM retornar_duracao_plano_por_industria_e_func(:employeeId, :industryId)",
            nativeQuery = true)
    PlanProjection findByIndustryPlan(@Param("employeeId") Long employeeId,
                                      @Param("industryId") Long industryId);


}
