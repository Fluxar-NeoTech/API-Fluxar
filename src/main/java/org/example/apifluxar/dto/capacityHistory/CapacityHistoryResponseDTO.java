package org.example.apifluxar.dto.capacityHistory;

import java.time.LocalDate;

public class CapacityHistoryResponseDTO {

    private LocalDate fullDate;
    private Double totalCapacity;

    public CapacityHistoryResponseDTO(Double totalCapacity, LocalDate fullDate) {
        this.totalCapacity = totalCapacity;
        this.fullDate = fullDate;
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
}
