package org.example.apifluxar.dto;

import jakarta.persistence.Column;

public class EmployeeRequestDTO {
    private String nome;
    private String sobrenome;
    private String email ;
    private String senha;
    private Character cargo;
    private String fotoPerfil;;
    private long setor_id;
    private long unidade_id;

    // Getter e Setter

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getCargo() {
        return cargo;
    }

    public void setCargo(Character cargo) {
        this.cargo = cargo;
    }

    public long getSetor_id() {
        return setor_id;
    }

    public void setSetor_id(long setor_id) {
        this.setor_id = setor_id;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public long getUnidade_id() {
        return unidade_id;
    }

    public void setUnidade_id(long unidade_id) {
        this.unidade_id = unidade_id;
    }

    public String getFotoPerfil() {return fotoPerfil;}

    public void setFotoPerfil(String fotoPerfil) {this.fotoPerfil = fotoPerfil;}
}
