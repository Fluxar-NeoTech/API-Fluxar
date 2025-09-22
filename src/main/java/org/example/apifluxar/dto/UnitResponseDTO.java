package org.example.apifluxar.dto;

import org.example.apifluxar.model.Industry;

public class UnitResponseDTO {
    private Long id;
    private String nome;
    private String cep;
    private String rua;
    private String cidade;
    private String estado;
    private String numero;
    private String bairro;
    private IndustryResponseDTO industry;

    public UnitResponseDTO(String nome, String cep, String rua, String cidade, String estado, String numero, String bairro) {
        this.nome = nome;
        this.cep = cep;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
    }

    public UnitResponseDTO(String nome, String cep, String rua, String cidade, String estado, String numero, String bairro, IndustryResponseDTO industry) {
        this.nome = nome;
        this.cep = cep;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
        this.industry = industry;
    }

    public UnitResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public IndustryResponseDTO getIndustry() {
        return industry;
    }

    public void setIndustry(IndustryResponseDTO industry) {
        this.industry = industry;
    }
}
