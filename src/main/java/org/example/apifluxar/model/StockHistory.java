package org.example.apifluxar.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "historicoestoque")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDateTime data;


    @Column(name = "capacidade_ocupada")
    private Integer capacidadeOcupada;

    @ManyToOne()
    @JoinColumn(name = "produto_id")
    private Product produto;

    @ManyToOne()
    @JoinColumn(name = "unidade_id")
    private Unit unidade;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector setor;

    @ManyToOne()
    @JoinColumn(name = "lote_id")
    private Batch lote;

    // Constructor

    public StockHistory() {}

    // Getters e Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDateTime getData() { return data; }

    public void setData(LocalDateTime data) { this.data = data; }

    public Integer getCapacidadeOcupada() { return capacidadeOcupada; }

    public void setCapacidadeOcupada(Integer capacidadeOcupada) { this.capacidadeOcupada = capacidadeOcupada; }

    public Product getProdutoId() {
        return produto;
    }

    public void setProdutoId(Product produto) {
        this.produto = produto;
    }

    public Unit getUnidadeId() {
        return unidade;
    }

    public void setUnidadeId(Unit unidade) {
        this.unidade = unidade;
    }

    public Sector getSetorId() {
        return setor;
    }

    public void setSetorId(Sector setor) {
        this.setor = setor;
    }

    public Batch getLoteId() {
        return lote;
    }

    public void setLoteId(Batch lote) {
        this.lote = lote;
    }
}
