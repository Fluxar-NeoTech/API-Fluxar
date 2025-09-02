package org.example.apifluxar.model;

import jakarta.persistence.*;

// We only read this table, so no further validations are needed.
@Entity
@Table(name = "funcionario")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String email ;
    private String senha;
    private Character cargo;
    @Column(name = "foto_perfil")
    private String fotoPerfil;
    @Column(name = "setor_id")
    private long setorId;
    @Column(name = "unidade_id")
    private long unidadeId;

    //Construct

    public Employee(Character cargo, String email, Long id, String nome, String senha, long setorId, String sobrenome, long unidadeId) {
        this.cargo = cargo;
        this.email = email;
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.setorId = setorId;
        this.sobrenome = sobrenome;
        this.unidadeId = unidadeId;
    }


    public Employee() {}

    //Getter e Setter
    public long getId() {
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public String getFotoPerfil() {return fotoPerfil;}

    public void setFotoPerfil(String fotoPerfil) {this.fotoPerfil = fotoPerfil;}

    public long getSetorId() {
        return setorId;
    }

    public void setSetorId(long setorId) {
        this.setorId = setorId;
    }

    public long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(long unidade_id) {
        this.unidadeId = unidadeId;
    }
}
