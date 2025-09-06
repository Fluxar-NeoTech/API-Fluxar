package org.example.apifluxar.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CapacityStockRequestDTO {

    @NotNull(message = "A largura não pode ser nula")
    @DecimalMin(value = "1.00", message = "A largura deve ser maior ou igual a 1")
    private Double largura;

    @NotNull(message = "A altura não pode ser nula")
    @DecimalMin(value = "1.00", message = "A altura deve ser maior ou igual a 1")
    private Double altura;

    @NotNull(message = "O comprimento não pode ser nulo")
    @DecimalMin(value = "1.00", message = "O comprimento deve ser maior ou igual a 1")
    private Double comprimento;

    private Long setorId;
    private Long unidadeId;


    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
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
}
