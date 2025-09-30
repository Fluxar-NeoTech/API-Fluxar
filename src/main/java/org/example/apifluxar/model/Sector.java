package org.example.apifluxar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Setor")
public class Sector {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    public Sector(String description, long id, String name) {
        this.description = description;
        this.id = id;
        this.name = name;
    }

    public Sector() {}

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
