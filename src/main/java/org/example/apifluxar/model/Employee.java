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
    @Column(name = "setor_id")
    private long setorId;
    @Column(name = "unidade_id")
    private long unidadeId;

    //Construct
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
