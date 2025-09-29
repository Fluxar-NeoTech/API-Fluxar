package org.example.apifluxar.dto.batch;

import org.example.apifluxar.dto.products.AllProductsResponseDTO;
import org.example.apifluxar.dto.unit.UnitBatchResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.dto.products.ProductResponseDTO;

import java.time.LocalDate;

public class BatchResponseCreateDTO {
    private String batchCode;
    private LocalDate expirationDate;
    private Double height;
    private Double length;
    private Double width;
    private Double volume;
    private AllProductsResponseDTO product;
    private UnitBatchResponseDTO unit;

    public BatchResponseCreateDTO(Double height, Double length, String batchCode, Double width, AllProductsResponseDTO product, UnitBatchResponseDTO unit, LocalDate expirationDate) {
        this.height = height;
        this.length = length;
        this.batchCode = batchCode;
        this.width = width;
        this.product = product;
        this.unit = unit;
        this.expirationDate = expirationDate;
    }

    public BatchResponseCreateDTO(String batchCode, LocalDate expirationDate, Double height, Double length, Double width, Double volume) {
        this.batchCode = batchCode;
        this.expirationDate = expirationDate;
        this.height = height;
        this.length = length;
        this.width = width;
        this.volume = volume;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchId) {
        this.batchCode = batchId;
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

    public UnitBatchResponseDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitBatchResponseDTO unit) {
        this.unit = unit;
    }

    public AllProductsResponseDTO getProduct() {
        return product;
    }

    public void setProduct(AllProductsResponseDTO product) {
        this.product = product;
    }
}
