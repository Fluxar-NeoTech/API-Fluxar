package org.example.apifluxar.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.apifluxar.validation.OnCreate;

public class ProductRequestDTO {

    @NotNull(message = "O nome do produto deve estar preenchido", groups = OnCreate.class)
    @Size(min = 1, max = 100, message = "O nome do produto deve ter entre 1 e 100 caracteres")
    private String name;

    @NotNull(message = "O tipo do produto deve estar preenchido", groups = OnCreate.class)
    @Size(min = 1, max = 100, message = "O tipo do produto deve ter entre 1 e 100 caracteres")
    private String type;

    @NotNull(message = "O setor deve estar preenchido", groups = OnCreate.class)
    private Long sectorId;

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

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }
}
