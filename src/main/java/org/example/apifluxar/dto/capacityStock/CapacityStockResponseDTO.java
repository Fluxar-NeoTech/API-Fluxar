package org.example.apifluxar.dto.capacityStock;

import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;

public class CapacityStockResponseDTO {

    private Double width;
    private Double height;
    private Double length;
    private Double maxCapacity;

    public CapacityStockResponseDTO(Double height, Double maxCapacity, Double length, Double width) {
        this.height = height;
        this.maxCapacity = maxCapacity;
        this.length = length;
        this.width = width;
    }

    public Double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

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
}
