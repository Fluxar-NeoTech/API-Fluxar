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

    @ManyToOne()
    @JoinColumn(name = "unidade_id")
    private Unit unidade;

    @ManyToOne()
    @JoinColumn(name  = "setor_id")
    private Sector setor;

    //Constructor

    public Employee() {}

    //Getters e Setters

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

    public Sector getSetor() {
        return setor;
    }

    public void setSetor(Sector setor) {
        this.setor = setor;
    }

    public Unit getUnidade() {
        return unidade;
    }

    public void setUnidade(Unit unidade) {
        this.unidade = unidade;
    }
}
