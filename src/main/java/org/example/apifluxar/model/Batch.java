package org.example.apifluxar.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;

@Entity
@Table(name = "lote")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_lote")
    private String batchCode;

    @Column(name = "validade")
    private LocalDate expirationDate;

    @Column(name = "altura")
    private Double height;

    @Column(name = "comprimento")
    private Double length;

    @Column(name = "largura")
    private Double width;

    @Formula("width * height * length")
    private Double volume;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unit unit;

    public Batch(String batchCode, LocalDate expirationDate, Double height, Double length, Double width, Product product, Unit unit) {
        this.batchCode = batchCode;
        this.expirationDate = expirationDate;
        this.height = height;
        this.length = length;
        this.width = width;
        this.product = product;
        this.unit = unit;
    }

    public Batch() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
