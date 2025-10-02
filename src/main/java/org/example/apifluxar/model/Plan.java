package org.example.apifluxar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "plano")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "preco")
    private Double price;

    @Column(name = "duracao_meses")
    private Integer monthsDuration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonthsDuration() {
        return monthsDuration;
    }

    public void setMonthsDuration(Integer monthsDuration) {
        this.monthsDuration = monthsDuration;
    }
}
