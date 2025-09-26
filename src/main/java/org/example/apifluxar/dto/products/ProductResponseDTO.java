package org.example.apifluxar.dto.products;

import org.example.apifluxar.dto.sector.SectorResponseDTO;

public class ProductResponseDTO {
    private String nome;
    private String tipo;
    private SectorResponseDTO setor;

    public ProductResponseDTO(String nome, String tipo, SectorResponseDTO setor) {
        this.nome = nome;
        this.tipo = tipo;
        this.setor = setor;
    }


    public ProductResponseDTO(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public SectorResponseDTO getSetor() {
        return setor;
    }
    public void setSetor(SectorResponseDTO setor) {
        this.setor = setor;
    }
}
