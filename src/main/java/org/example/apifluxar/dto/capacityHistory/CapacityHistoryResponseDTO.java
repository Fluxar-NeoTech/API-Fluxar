package org.example.apifluxar.dto.capacityHistory;

import java.time.LocalDate;

public class CapacityHistoryResponseDTO {

    private LocalDate fullDate;
    private Double totalCapacity;
    private Double occupancyPercentage;

    public CapacityHistoryResponseDTO(Double totalCapacity, LocalDate fullDate, Double occupancyPercentage) {
        this.totalCapacity = totalCapacity;
        this.fullDate = fullDate;
        this.occupancyPercentage = occupancyPercentage;
    }

    public CapacityHistoryResponseDTO() {
    }

    public LocalDate getFullDate() {
        return fullDate;
    }

    public void setFullDate(LocalDate fullDate) {
        this.fullDate = fullDate;
    }

    public Double getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Double getOccupancyPercentage() {
        return occupancyPercentage;
    }

    public void setOccupancyPercentage(Double occupancyPercentage) {
        this.occupancyPercentage = occupancyPercentage;
    }
}
