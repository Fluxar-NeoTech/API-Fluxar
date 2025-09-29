package org.example.apifluxar.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "capacidadeestoque")
public class CapacityStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "largura")
    private Double width;

    @Column(name = "altura")
    private Double height;

    @Column(name = "comprimento")
    private Double length;

    @Formula("largura * altura * comprimento")
    @Column(name = "capacidade_maxima")
    private Double maxCapacity;

    @ManyToOne()
    @JoinColumn(name = "setor_id", nullable = false)
    private Sector sector;

    @ManyToOne()
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unit unit;

    public CapacityStock() {}

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Double getWidth() { return width; }

    public void setWidth(Double width) { this.width = width; }

    public Double getHeight() { return height; }

    public void setHeight(Double height) { this.height = height; }

    public Double getLength() { return length; }

    public void setLength(Double length) { this.length = length; }

    public Double getMaxCapacity() { return maxCapacity; }

    public void setMaxCapacity(Double maxCapacity) { this.maxCapacity = maxCapacity; }

    public Sector getSector() { return sector; }

    public void setSector(Sector sector) { this.sector = sector; }

    public Unit getUnit() { return unit; }

    public void setUnit(Unit unit) { this.unit = unit; }
}
