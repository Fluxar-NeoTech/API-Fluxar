package org.example.apifluxar.dto.employee;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.apifluxar.validation.OnCreate;

public class EmployeeRequestDTO {
    @NotNull(message = "O email não pode ser nulo", groups = OnCreate.class)
    @Min(value = 5, message = "O email deve ter no mínimo 5 caracteres", groups = OnCreate.class)
    @NotBlank(message = "O email não pode ser em branco", groups = OnCreate.class)
    private String email;

    @NotNull(message = "A senha não pode ser nula", groups = OnCreate.class)
    @Min(value = 6, message = "A senha deve ter no mínimo 6 caracteres", groups = OnCreate.class)
    @NotBlank(message = "A senha não pode ser em branco", groups = OnCreate.class)
    private String password;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
