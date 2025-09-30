package org.example.apifluxar.dto.capacityStock;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CapacityStockRequestDTO {

    @NotNull(message = "Largura não pode ser nula")
    @DecimalMin(value = "1.00", message = "Largura deve ser maior ou igual a 1")
    private Double width;

    @NotNull(message = "Altura não pode ser nula")
    @DecimalMin(value = "1.00", message = "Altura deve ser maior ou igual a 1")
    private Double height;

    @NotNull(message = "Comprimento não pode ser nulo")
    @DecimalMin(value = "1.00", message = "Comprimento deve ser maior ou igual a 1")
    private Double length;

    @NotNull(message = "Id do setor não pode ser nulo")
    private Long sectorId;

    @NotNull(message = "Id da unidade não pode ser nulo")
    private Long unitId;

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
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

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
}
