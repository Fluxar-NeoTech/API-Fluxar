package org.example.apifluxar.dto.employee;

import jakarta.validation.constraints.NotNull;
import org.example.apifluxar.validation.OnCreate;

public class EmployeeRequestDTO {
    @NotNull(message = "O campo email não deve ser nulo")
    private String email ;
    @NotNull(message = "O campo senha não deve ser nulo", groups = OnCreate.class)
    private String senha;

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
}
