package org.example.apifluxar.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "HistoricoEstoque")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @Column(name = "capacidade_ocupada")
    private Integer capacidadeOcupada;

    @Column(name = "produto_id")
    private Long produtoId;

    @Column(name = "unidade_id")
    private Long unidadeId;

    @Column(name = "setor_id")
    private Long setorId;

    @Column(name = "lote_id")
    private String loteId;

    // Constructor

    public StockHistory() {}

    // Getters e Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getData() { return data; }

    public void setData(LocalDate data) { this.data = data; }

    public Integer getCapacidadeOcupada() { return capacidadeOcupada; }

    public void setCapacidadeOcupada(Integer capacidadeOcupada) { this.capacidadeOcupada = capacidadeOcupada; }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public Long getSetorId() {
        return setorId;
    }

    public void setSetorId(Long setorId) {
        this.setorId = setorId;
    }

    public String getLoteId() {
        return loteId;
    }

    public void setLoteId(String loteId) {
        this.loteId = loteId;
    }
}
