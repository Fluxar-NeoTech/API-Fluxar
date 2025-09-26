package org.example.apifluxar.dto.capacityStock;

import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;

public class CapacityStockResposeDTO {

    private Double largura;
    private Double altura;
    private Double comprimento;
    private Double capacidadeMaxima;
    private SectorResponseDTO setor;
    private UnitResponseDTO unidade;

    public CapacityStockResposeDTO(Double altura, Double capacidadeMaxima, Double comprimento, Double largura) {
        this.altura = altura;
        this.capacidadeMaxima = capacidadeMaxima;
        this.comprimento = comprimento;
        this.largura = largura;
    }

    public Double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

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

    public SectorResponseDTO getSetor() {
        return setor;
    }

    public void setSetor(SectorResponseDTO setor) {
        this.setor = setor;
    }

    public UnitResponseDTO getUnidade() {
        return unidade;
    }

    public void setUnidade(UnitResponseDTO unidade) {
        this.unidade = unidade;
    }
}
