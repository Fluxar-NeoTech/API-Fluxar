package org.example.apifluxar.dto;

import java.time.LocalDate;

public class BatchResponseDTO {
    private String idLote;
    private LocalDate validade;
    private Double altura;
    private Double comprimento;
    private Double largura;
    private Double volume;
    private ProductResponseDTO product;
    private UnitResponseDTO unit;

    public BatchResponseDTO(Double altura, Double comprimento, String idLote, Double largura, ProductResponseDTO product, UnitResponseDTO unit, LocalDate validade) {
        this.altura = altura;
        this.comprimento = comprimento;
        this.idLote = idLote;
        this.largura = largura;
        this.product = product;
        this.unit = unit;
        this.validade = validade;
    }

    public BatchResponseDTO(String idLote, LocalDate validade, Double altura, Double comprimento, Double largura, Double volume) {
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

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
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



    public ProductResponseDTO getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDTO product) {
        this.product = product;
    }

    public UnitResponseDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitResponseDTO unit) {
        this.unit = unit;
    }
}