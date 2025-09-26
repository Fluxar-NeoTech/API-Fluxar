package org.example.apifluxar.dto.capacityStock;

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

    @NotNull(message = "o id do setor não pode ser nulo")
    private Long setorId;
    @NotNull(message = "o id da unidade não pode ser nulo")
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

    public  Long getSetorId() {
        return setorId;
    }

    public void setSetorId( Long setorId) {
        this.setorId = setorId;
    }

    public @NotNull(message = "o id da unidade não pode ser nulo") Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(@NotNull(message = "o id da unidade não pode ser nulo") Long unidadeId) {
        this.unidadeId = unidadeId;
    }
}
