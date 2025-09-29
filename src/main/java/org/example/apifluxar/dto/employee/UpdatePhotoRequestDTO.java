package org.example.apifluxar.dto.employee;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.apifluxar.validation.OnCreate;
import org.example.apifluxar.validation.OnPatch;

public class UpdatePhotoRequestDTO {
    @NotNull(message = "O email não pode ser nulo", groups = OnPatch.class)
    @Min(value = 5, message = "O email deve ter no mínimo 5 caracteres", groups = OnCreate.class)
    @NotBlank(message = "O email não pode ser em branco", groups = OnPatch.class)
    private String email;

    @NotNull(message = "A foto de perfil do usuário não pode ser nula", groups = OnPatch.class)
    @NotBlank(message = "A foto de perfil do usuário não pode ser em branco", groups = OnPatch.class)
    private String profilePhoto;

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
