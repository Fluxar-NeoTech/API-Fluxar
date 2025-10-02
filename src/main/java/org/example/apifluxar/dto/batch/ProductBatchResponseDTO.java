package org.example.apifluxar.dto.batch;

import java.time.LocalDate;

public class ProductBatchResponseDTO {
    private String batchCode;
    private LocalDate expirationDate;
    private Double height;
    private Double length;
    private Double width;
    private String productName;
//    private UnitResponseDTO unit;

//    public BatchResponseDTO(Double height, Double length, String batchCode, Double width, ProductResponseDTO product, UnitResponseDTO unit, LocalDate expirationDate) {
//        this.height = height;
//        this.length = length;
//        this.batchCode = batchCode;
//        this.width = width;
//        this.product = product;
////        this.unit = unit;
//        this.expirationDate = expirationDate;
//    }

    public ProductBatchResponseDTO(String batchCode, LocalDate expirationDate, Double height, Double length, Double width, String productName) {
        this.productName = productName;
        this.batchCode = batchCode;
        this.expirationDate = expirationDate;
        this.height = height;
        this.length = length;
        this.width = width;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    //    public UnitResponseDTO getUnit() {
//        return unit;
//    }
//
//    public void setUnit(UnitResponseDTO unit) {
//        this.unit = unit;
//    }

}
