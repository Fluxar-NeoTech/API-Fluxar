package org.example.apifluxar.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historicoestoque")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "capacidade_ocupada")
    private Integer occupiedCapacity;

    @ManyToOne()
    @JoinColumn(name = "produto_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "unidade_id")
    private Unit unit;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector sector;

    @ManyToOne()
    @JoinColumn(name = "lote_id")
    private Batch batch;

    public StockHistory() {}

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public Integer getOccupiedCapacity() { return occupiedCapacity; }

    public void setOccupiedCapacity(Integer occupiedCapacity) { this.occupiedCapacity = occupiedCapacity; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public Unit getUnit() { return unit; }

    public void setUnit(Unit unit) { this.unit = unit; }

    public Sector getSector() { return sector; }

    public void setSector(Sector sector) { this.sector = sector; }

    public Batch getBatch() { return batch; }

    public void setBatch(Batch batch) { this.batch = batch; }
}
