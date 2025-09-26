package org.example.apifluxar.dto.batch;

import java.time.LocalDate;

public class BatchRequestDTO {

    private String idLote;
    private LocalDate validade;
    private Double altura;
    private Double comprimento;
    private Double largura;
    private Long unitId;
    private Long productId;

    public String getIdLote() {
        return idLote;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public Double getLargura() {
        return largura;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}