package org.example.apifluxar.dto;

import java.time.LocalDate;

public class CapacityHistoryResponseDTO {

    private LocalDate dataCompleta;
    private Integer capacidadeTotal;

    public LocalDate getDataCompleta() {
        return dataCompleta;
    }

    public void setDataCompleta(LocalDate dataCompleta) {
        this.dataCompleta = dataCompleta;
    }

    public Integer getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(Integer capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }
}
