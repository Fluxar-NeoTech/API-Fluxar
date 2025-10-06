package org.example.apifluxar.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_active_users")
public class DailyActiveUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Employee employee;

    @Column(name = "data_acesso")
    private LocalDate accessDate;

    @Column(name = "hora_acesso")
    private LocalDateTime accessTime;

    @Column(name = "origem")
    private String origin;

    public DailyActiveUsers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(LocalDate accessDate) {
        this.accessDate = accessDate;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
