package org.example.apifluxar.dto.products;

import org.example.apifluxar.dto.sector.SectorResponseDTO;

public class ProductResponseDTO {
    private String name;
    private String type;
    private SectorResponseDTO sector;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(String name, String type, SectorResponseDTO sector) {
        this.name = name;
        this.type = type;
        this.sector = sector;
    }

    public ProductResponseDTO(String name, String type) {
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

    public SectorResponseDTO getSector() {
        return sector;
    }

    public void setSector(SectorResponseDTO sector) {
        this.sector = sector;
    }
}
