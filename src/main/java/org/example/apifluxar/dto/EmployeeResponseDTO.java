package org.example.apifluxar.dto;

import jakarta.persistence.Column;

public class EmployeeResponseDTO {
    private long id;
    private String nome;
    private String sobrenome;
    private String fotoPerfil;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getFotoPerfil() {return fotoPerfil;}

    public void setFotoPerfil(String fotoPerfil) {this.fotoPerfil = fotoPerfil;}
}
