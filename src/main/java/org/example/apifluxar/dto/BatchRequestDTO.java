package org.example.apifluxar.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class BatchRequestDTO {

    private String idLote;
    private LocalDate validade;
    private Double altura;
    private Double comprimento;
    private Double largura;
    private String nomeProduto;
    private Long unidade;

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

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public String getNomeProduto(){
        return nomeProduto;
    }

    public Long getUnidade() {
        return unidade;
    }

    public void setUnidade(Long unidade) {
        this.unidade = unidade;
    }
}
