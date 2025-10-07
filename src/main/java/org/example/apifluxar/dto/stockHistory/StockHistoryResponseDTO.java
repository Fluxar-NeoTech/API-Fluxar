package org.example.apifluxar.dto.stockHistory;

public class StockHistoryResponseDTO {

    private Character movement;
    private Double volumeHandled;
    private String date;

    public StockHistoryResponseDTO(Character movement, Double volumeHandled, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
