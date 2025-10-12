package org.example.apifluxar.dto.batch;

import org.example.apifluxar.dto.product.ProductResponseDTO;

import java.time.LocalDate;

public class BatchResponseDTO {
    private String batchCode;
    private LocalDate expirationDate;
    private Double height;
    private Double length;
    private Double width;
    private Double volume;
    private ProductResponseDTO product;

    public BatchResponseDTO(String batchCode, LocalDate expirationDate, Double height, Double length, Double width, Double volume, ProductResponseDTO productResponseDTO) {
        this.product = productResponseDTO;
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

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
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

    public ProductResponseDTO getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDTO product) {
        this.product = product;
    }
}
