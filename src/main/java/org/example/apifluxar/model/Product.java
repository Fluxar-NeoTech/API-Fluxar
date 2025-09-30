package org.example.apifluxar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "tipo")
    private String type;

    @ManyToOne()
    @JoinColumn(name = "setor_id")
    private Sector sector;

    public Product() {}

    public Product(String name, String type, Sector sector) {
        this.name = name;
        this.type = type;
        this.sector = sector;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Sector getSector() { return sector; }

    public void setSector(Sector sector) { this.sector = sector; }
}
