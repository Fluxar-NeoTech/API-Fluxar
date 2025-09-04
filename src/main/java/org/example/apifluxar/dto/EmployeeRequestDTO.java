package org.example.apifluxar.dto;

import jakarta.persistence.Column;

public class EmployeeRequestDTO {
    private String email ;
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
