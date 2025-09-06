package org.example.apifluxar.dto;

import jakarta.validation.constraints.NotNull;
import org.example.apifluxar.validation.OnPatch;

public class UpdatePhotoRequestDTO {
    @NotNull(message = "O campo email não deve ser nulo")
    private String email ;

    @NotNull(message = "O campo foto de perfil não deve ser nulo", groups = OnPatch.class)
    private String fotoPerfil;

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
