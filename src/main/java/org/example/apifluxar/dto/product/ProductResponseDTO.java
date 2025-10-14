package org.example.apifluxar.dto.product;

public class ProductResponseDTO {
    private Long id;
    private String name;
    private String type;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
