package org.example.apifluxar.dto.capacityHistory;

public class CapacityHistoryOccupationResponse {
    Double occupancyPercentage;
    Double remainingVolume;

    public CapacityHistoryOccupationResponse(Double occupancyPercentage, Double remainingVolume) {
        this.occupancyPercentage = occupancyPercentage;
        this.remainingVolume = remainingVolume;
    }

    public Double getOccupancyPercentage() {
        return occupancyPercentage;
    }

    public void setOccupancyPercentage(Double occupancyPercentage) {
        this.occupancyPercentage = occupancyPercentage;
    }

    public Double getRemainingVolume() {
        return remainingVolume;
    }

    public void setRemainingVolume(Double remainingVolume) {
        this.remainingVolume = remainingVolume;
    }
}
