package org.example.apifluxar.projection;

public interface CapacityHistoryProjection {
    Double getOccupancyPercentage();
    Double getRemainingVolume();
}
