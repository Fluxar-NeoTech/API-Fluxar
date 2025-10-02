package org.example.apifluxar.dto.products;

import org.example.apifluxar.dto.sector.SectorResponseDTO;

public class ProductResponseDTO {
    private Long id;
    private String name;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

//    public ProductResponseDTO(String name, String type, SectorResponseDTO sector) {
//        this.name = name;
//        this.type = type;
//        this.sector = sector;
//    }
//
//    public ProductResponseDTO(String name, String type) {
//        this.name = name;
//        this.type = type;
//    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public SectorResponseDTO getSector() {
//        return sector;
//    }
//
//    public void setSector(SectorResponseDTO sector) {
//        this.sector = sector;
//    }
}
