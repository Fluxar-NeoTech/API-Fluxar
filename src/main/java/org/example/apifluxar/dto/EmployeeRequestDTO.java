package org.example.apifluxar.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import org.example.apifluxar.validation.OnCreate;
import org.example.apifluxar.validation.OnPatch;

public class EmployeeRequestDTO {
    @NotNull(message = "O campo email não deve ser nulo")
    private String email ;
    @NotNull(message = "O campo senha não deve ser nulo", groups = OnCreate.class)
    private String senha;
    @NotNull(message = "O campo foto de perfil não deve ser nulo", groups = OnPatch.class)
    private String fotoPerfil;

    // Getter e Setter
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

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
