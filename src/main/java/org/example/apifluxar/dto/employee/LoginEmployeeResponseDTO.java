package org.example.apifluxar.dto.employee;

public class LoginEmployeeResponseDTO {
    private Long id;
    private Character role;
    private String email;

    public LoginEmployeeResponseDTO(Long id, Character role, String email) {
        this.id = id;
        this.role = role;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getRole() {
        return role;
    }

    public void setRole(Character role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
