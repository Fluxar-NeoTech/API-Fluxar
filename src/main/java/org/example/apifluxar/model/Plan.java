package org.example.apifluxar.model;

import jakarta.persistence.*;

@Table(name = "plano")
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;
    @Column(name = "duracao_meses")
    private Integer duracaoMeses;

    //Construct

    public Plan(Long id, String nome, Double preco, Integer duracaoMeses) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracaoMeses = duracaoMeses;
    }

    public Plan() {}

    //Getter e Setter


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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(Integer duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    //ToString

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", duracaoMeses=" + duracaoMeses +
                '}';
    }
}
