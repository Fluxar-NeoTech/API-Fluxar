package org.example.apifluxar.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "historicocapacidade")
public class CapacityHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_completa")
    private LocalDate fullDate;

    @Column(name = "capacidade_total_ocupada")
    private Integer totalCapacity;

    @ManyToOne()
    @JoinColumn(name = "unidade_id")
    private Unit unit;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector sector;

    public CapacityHistory() {}

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getFullDate() { return fullDate; }

    public void setFullDate(LocalDate fullDate) { this.fullDate = fullDate; }

    public Integer getTotalCapacity() { return totalCapacity; }

    public void setTotalCapacity(Integer totalCapacity) { this.totalCapacity = totalCapacity; }

    public Sector getSector() { return sector; }

    public void setSector(Sector sector) { this.sector = sector; }

    public Unit getUnit() { return unit; }

    public void setUnit(Unit unit) { this.unit = unit; }
}
