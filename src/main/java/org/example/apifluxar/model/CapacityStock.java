package org.example.apifluxar.model;

import jakarta.persistence.*;

import java.lang.Double;

@Table(name = "CapacidadeEstoque")
@Entity
public class CapacityStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double largura;
    private Double altura;
    private Double comprimento;

    @Column(name = "capacidade_maxima", insertable = false, updatable = false)
    private Double capacidadeMaxima;

    @Column(name = "setor_id")
    private Long setorId;

    @Column(name = "unidade_id")
    private Long unidadeId;

    //Constructor

    public CapacityStock() {
    }

    // Getters e Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public Double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public Long getSetorId() {
        return setorId;
    }

    public void setSetorId(Long setorId) {
        this.setorId = setorId;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }
}
