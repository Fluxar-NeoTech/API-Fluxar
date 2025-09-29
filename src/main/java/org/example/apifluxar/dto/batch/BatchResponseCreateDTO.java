package org.example.apifluxar.dto.batch;

import org.example.apifluxar.dto.products.AllProductsResponseDTO;
import org.example.apifluxar.dto.unit.UnitBatchResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.dto.products.ProductResponseDTO;

import java.time.LocalDate;

public class BatchResponseCreateDTO {
    private String idLote;
    private LocalDate validade;
    private Double altura;
    private Double comprimento;
    private Double largura;
    private Double volume;
    private AllProductsResponseDTO product;
    private UnitBatchResponseDTO unit;

    public BatchResponseCreateDTO(Double altura, Double comprimento, String idLote, Double largura, AllProductsResponseDTO product, UnitBatchResponseDTO unit, LocalDate validade) {
        this.altura = altura;
        this.comprimento = comprimento;
        this.idLote = idLote;
        this.largura = largura;
        this.product = product;
        this.unit = unit;
        this.validade = validade;
    }

    public BatchResponseCreateDTO(String idLote, LocalDate validade, Double altura, Double comprimento, Double largura, Double volume) {
        this.idLote = idLote;
        this.validade = validade;
        this.altura = altura;
        this.comprimento = comprimento;
        this.largura = largura;
        this.volume = volume;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public UnitBatchResponseDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitBatchResponseDTO unit) {
        this.unit = unit;
    }

    public AllProductsResponseDTO getProduct() {
        return product;
    }

    public void setProduct(AllProductsResponseDTO product) {
        this.product = product;
    }
}
