package org.example.apifluxar.dto.capacityHistory;

import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;

import java.time.LocalDate;

public class CapacityHistoryResponseDTO {

    private LocalDate dataCompleta;
    private Integer capacidadeTotal;

    public CapacityHistoryResponseDTO(Integer capacidadeTotal, LocalDate dataCompleta) {
        this.capacidadeTotal = capacidadeTotal;
        this.dataCompleta = dataCompleta;

    }

    public CapacityHistoryResponseDTO() {
    }

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
