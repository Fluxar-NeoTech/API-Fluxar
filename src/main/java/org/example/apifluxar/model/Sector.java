package org.example.apifluxar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "Setor")
@Entity
public class Sector {
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String descricao;

    public Sector(String descricao, long id, String nome) {
        this.descricao = descricao;
        this.id = id;
        this.nome = nome;
    }

    public Sector() {}
    //Getter e Setter
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
