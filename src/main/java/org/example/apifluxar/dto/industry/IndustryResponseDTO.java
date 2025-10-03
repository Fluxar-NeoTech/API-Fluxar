package org.example.apifluxar.dto.industry;

public class IndustryResponseDTO {
    private Long id;
    private String name;
    private String cnpj;

//    public IndustryResponseDTO() {
//    }
//
//    public IndustryResponseDTO(Long id, String name, String cnpj) {
//        this.id = id;
//        this.name = name;
//        this.cnpj = cnpj;
//    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
