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
    public String getType() {
        return type;
    }

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
