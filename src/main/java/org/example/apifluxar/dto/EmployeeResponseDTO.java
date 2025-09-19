package org.example.apifluxar.dto;

import jakarta.persistence.Column;
import org.example.apifluxar.model.Sector;

public class EmployeeResponseDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private Character cargo;
    private String fotoPerfil;
    private SectorResponseDTO setor;
    private UnitResponseDTO unit;


    public EmployeeResponseDTO(Long id, String nome, String sobrenome, String email, Character cargo, String foto) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cargo = cargo;
        this.fotoPerfil = foto;
    }

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

    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return email;}

    public SectorResponseDTO getSetor() {
        return setor;
    }
    public void setSetor(SectorResponseDTO setor) {
        this.setor = setor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getCargo() {
        return cargo;
    }

    public void setCargo(Character cargo) {
        this.cargo = cargo;
    }

    public UnitResponseDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitResponseDTO unit) {
        this.unit = unit;
    }
}
