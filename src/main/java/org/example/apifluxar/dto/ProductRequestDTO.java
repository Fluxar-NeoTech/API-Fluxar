package org.example.apifluxar.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.validation.OnCreate;

public class ProductRequestDTO {

    @NotNull(message = "O nome do produto deve estar preenchido", groups = OnCreate.class)
    @Size(min = 1, max = 100, message = "O nome do produto deve ter entre 1 e 100 caracteres")
    private String nome;

    @NotNull(message = "O tipo do produto deve estar preenchido", groups = OnCreate.class)
    @Size(min = 1, max = 100, message = "O tipo do produto deve ter entre 1 e 100 caracteres")
    private String tipo;
    @NotNull(message = "O setor deve estar preenchido", groups = OnCreate.class)
    private Long setor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo() {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getSetor() {
        return setor;
    }

    public void setSetor( Long setor) {
        this.setor = setor;
    }
}
