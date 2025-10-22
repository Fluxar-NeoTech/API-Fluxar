package org.example.apifluxar.repository;

import org.example.apifluxar.model.Unit;
import org.example.apifluxar.projection.UnitIndustryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit,Long> {
    @Query("SELECT u FROM Unit u WHERE u.industry.id = :id")
    List<Unit> findAllByIndustry(@Param("id")Long id);

    @Query(value = "SELECT unidade_id as UnitId, disponibilidade as Availability \n" +
            "FROM disponibilidade_por_industria(:industryId)", nativeQuery = true)
    List<UnitIndustryProjection> findAvailabilityByIndustry(@Param("industryId")Long id);

    @Query(value = "SELECT * FROM " +
            "disponibilidade_ocupacao_unidade(:unitId)", nativeQuery = true)
    Long findAvailabilityByUnitId(@Param("unitId")Long unitId);
}
