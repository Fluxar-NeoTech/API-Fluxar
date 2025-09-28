package org.example.apifluxar.dto.unit;

import org.example.apifluxar.dto.industry.IndustryResponseDTO;

public class UnitBatchResponseDTO {
    private String nome;
    private String cep;
    private String rua;
    private String cidade;
    private String estado;
    private String numero;
    private String bairro;

    public UnitBatchResponseDTO(String nome, String cep, String rua, String cidade, String estado, String numero, String bairro) {
        this.nome = nome;
        this.cep = cep;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
