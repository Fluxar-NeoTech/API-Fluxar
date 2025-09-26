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
    private LocalDate dataCompleta;

    @Column(name = "capacidade_total_ocupada")
    private Integer capacidadeTotal;

    @ManyToOne()
    @JoinColumn(name = "unidade_id")
    private Unit unidade;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector sector;

    // Constructor

    public CapacityHistory() {}

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCompleta() {
        return dataCompleta;
    }

    public void setDataCompleta(LocalDate dataCompleta) {
        this.dataCompleta = dataCompleta;
    }

    public Integer getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(Integer capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Unit getUnidade() {
        return unidade;
    }

    public void setUnidade(Unit unidade) {
        this.unidade = unidade;
    }


}
