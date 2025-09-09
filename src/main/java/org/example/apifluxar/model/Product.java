package org.example.apifluxar.model;

import jakarta.persistence.*;

@Table(name = "Produto")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector setor;

    // Constructor

    public Product() {}

    public Product(String nome, String tipo, Sector setor) {
        this.nome = nome;
        this.tipo = tipo;
        this.setor = setor;
    }

    // Getters e Setters

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Sector getSetor() {
        return setor;
    }

    public void setSetor(Sector setor) {
        this.setor = setor;
    }
}
