package org.example.apifluxar.dto.stockHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StockHistoryResponseDTO {

    private Character movement;
    private Double volumeHandled;
    private LocalDateTime date;

    public StockHistoryResponseDTO(Character movement, Double volumeHandled, LocalDateTime date) {
        this.movement = movement;
        this.volumeHandled = volumeHandled;
        this.date = date;
    }

    public Character getMovement() {
        return movement;
    }

    public void setMovement(Character movement) {
        this.movement = movement;
    }

    public Double getVolumeHandled() {
        return volumeHandled;
    }

    public void setVolumeHandled(Double volumeHandled) {
        this.volumeHandled = volumeHandled;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
