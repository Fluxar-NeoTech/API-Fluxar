package org.example.apifluxar.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.lang.Double;

@Table(name = "capacidadeestoque")
@Entity
public class CapacityStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double largura;
    private Double altura;
    private Double comprimento;

    @Formula("largura * altura * comprimento")
    private Double capacidadeMaxima;

    @ManyToOne()
    @JoinColumn(name = "setor_id", nullable = false)
    private Sector setor;

    @ManyToOne()
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unit unidade;

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

    public void setCapacidadeMaxima(Double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Sector getSetor() {
        return setor;
    }

    public void setSetor(Sector setor) {
        this.setor = setor;
    }

    public Unit getUnidade() {
        return unidade;
    }

    public void setUnidade(Unit unidade) {
        this.unidade = unidade;
    }
}
