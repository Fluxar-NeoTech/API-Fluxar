package org.example.apifluxar.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "historico_capacidade")
public class CapacityHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_completa")
    private LocalDate fullDate;

    @Column(name = "capacidade_total_ocupada")
    private Double totalCapacity;

    @Column(name = "porcentagem_ocupacao")
    private Double occupancyPercentage;

    @ManyToOne()
    @JoinColumn(name = "unidade_id")
    private Unit unit;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector sector;

    @ManyToOne()
    @JoinColumn(name = "produto_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "industria_id")
    private Industry industria;

    public CapacityHistory() {}

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getFullDate() { return fullDate; }

    public void setFullDate(LocalDate fullDate) { this.fullDate = fullDate; }

    public Sector getSector() { return sector; }

    public void setSector(Sector sector) { this.sector = sector; }

    public Unit getUnit() { return unit; }

    public void setUnit(Unit unit) { this.unit = unit; }

    public Double getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Double getOccupancyPercentage() {
        return occupancyPercentage;
    }

    public void setOccupancyPercentage(Double occupancyPercentage) {
        this.occupancyPercentage = occupancyPercentage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Industry getIndustria() {
        return industria;
    }

    public void setIndustria(Industry industria) {
        this.industria = industria;
    }
}
