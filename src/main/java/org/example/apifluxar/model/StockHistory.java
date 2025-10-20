package org.example.apifluxar.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_estoque")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "movimentacao")
    private Character movement;

    @Column(name = "volume_movimentado")
    private Double volumeHandled;

    @ManyToOne()
    @JoinColumn(name = "produto_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "unidade_id")
    private Unit unit;

    @ManyToOne()
    @JoinColumn(name = "industria_id")
    private Industry industry;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector sector;

    public StockHistory() {}

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public Unit getUnit() { return unit; }

    public void setUnit(Unit unit) { this.unit = unit; }

    public Character getMovement() {
        return movement;
    }

    public void setMovement(Character movement) {
        this.movement = movement;
    }

    public Double getVolumeHandled() {
        return volumeHandled;
    }

    public void setVolumeHandled(Double volumeHandled) {
        this.volumeHandled = volumeHandled;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
