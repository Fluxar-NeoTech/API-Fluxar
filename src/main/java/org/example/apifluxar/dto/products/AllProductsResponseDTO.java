package org.example.apifluxar.dto.products;

public class AllProductsResponseDTO {
    private String name;
    private String type;

    public AllProductsResponseDTO() {
    }

    public AllProductsResponseDTO(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
