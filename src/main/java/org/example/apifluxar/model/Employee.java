package org.example.apifluxar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String firstName;

    @Column(name = "sobrenome")
    private String lastName;

    private String email;

    @Column(name = "senha")
    private String password;

    @Column(name = "cargo")
    private Character role;

    @Column(name = "foto_perfil")
    private String profilePicture;

    @ManyToOne()
    @JoinColumn(name = "unidade_id")
    private Unit unit;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector sector;

    public Employee() {}

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Character getRole() { return role; }

    public void setRole(Character role) { this.role = role; }

    public String getProfilePicture() { return profilePicture; }

    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    public Sector getSector() { return sector; }

    public void setSector(Sector sector) { this.sector = sector; }

    public Unit getUnit() { return unit; }

    public void setUnit(Unit unit) { this.unit = unit; }
}
