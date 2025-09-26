package org.example.apifluxar.repository;

import org.example.apifluxar.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit,Long> {
    @Query("SELECT u FROM Unit u WHERE u.industry.id = :id")
    List<Unit> findAllByIndustryId(@Param("id")Long id);
}
