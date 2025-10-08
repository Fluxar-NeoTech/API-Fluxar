package org.example.apifluxar.repository;

import jakarta.transaction.Transactional;
import org.example.apifluxar.model.DailyActiveUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DailyActiveUsersRepository extends JpaRepository<DailyActiveUsers, Long> {
    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO daily_active_users (user_id, data_acesso, hora_acesso, origem)
    VALUES (:userId, CURRENT_DATE, CURRENT_TIME, :origin)
    """, nativeQuery = true)
    void insertAccess(
            @Param("userId") Long userId,
            @Param("origin") String origin
    );
}
