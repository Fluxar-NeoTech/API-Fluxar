package org.example.apifluxar.dto.capacityHistory;

import java.time.LocalDate;

public class CapacityHistoryResponseDTO {

    private LocalDate fullDate;
    private Integer totalCapacity;

    public CapacityHistoryResponseDTO(Integer totalCapacity, LocalDate fullDate) {
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

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
}
