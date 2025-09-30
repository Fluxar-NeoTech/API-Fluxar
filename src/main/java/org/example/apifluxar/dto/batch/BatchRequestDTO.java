package org.example.apifluxar.dto.batch;

import java.time.LocalDate;

public class BatchRequestDTO {

    private String batchCode;
    private LocalDate expirationDate;
    private Double height;
    private Double length;
    private Double width;
    private Long unitId;
    private Long productId;

    public String getBatchCode() {
        return batchCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Double getHeight() {
        return height;
    }

    public Double getLength() {
        return length;
    }

    public Double getWidth() {
        return width;
    }

    public void setBatchCode(String batchId) {
        this.batchCode = batchId;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
