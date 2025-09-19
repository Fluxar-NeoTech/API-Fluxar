package org.example.apifluxar.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.model.Unit;

import java.time.LocalDate;

public class CapacityHistoryResponseDTO {

    private LocalDate dataCompleta;
    private Integer capacidadeTotal;
    private ProductResponseDTO produto;
    private UnitResponseDTO unidade;
    private SectorResponseDTO sector;
    private BatchResponseDTO lote;

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

    public ProductResponseDTO getProduto() {
        return produto;
    }

    public void setProduto(ProductResponseDTO produto) {
        this.produto = produto;
    }

    public SectorResponseDTO getSector() {
        return sector;
    }

    public void setSector(SectorResponseDTO sector) {
        this.sector = sector;
    }

    public UnitResponseDTO getUnidade() {
        return unidade;
    }

    public void setUnidade(UnitResponseDTO unidade) {
        this.unidade = unidade;
    }

    public BatchResponseDTO getLote() {
        return lote;
    }

    public void setLote(BatchResponseDTO lote) {
        this.lote = lote;
    }
}
