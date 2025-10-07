package org.example.apifluxar.dto.batch;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.example.apifluxar.validation.OnCreate;

import java.time.LocalDate;

public class BatchRequestDTO {

    @NotBlank(message = "O código do lote é obrigatório", groups = OnCreate.class)
    @Size(min = 1, max = 30, message = "O código do lote deve ter entre {min} e {max} caracteres")
    private String batchCode;

    @NotNull(message = "A data de validade é obrigatória", groups = OnCreate.class)
    @Future(message = "A data de validade deve ser posterior à data atual")
    private LocalDate expirationDate;

    @NotNull(message = "A altura do produto é obrigatória", groups = OnCreate.class)
    @DecimalMin(value = "1.00", message = "Altura deve ser maior ou igual a 1")
    private Double height;

    @NotNull(message = "O comprimento do produto é obrigatório", groups = OnCreate.class)
    @DecimalMin(value = "1.00", message = "Comprimentro deve ser maior ou igual a 1")
    private Double length;

    @NotNull(message = "A largura do produto é obrigatória", groups = OnCreate.class)
    @DecimalMin(value = "1.00", message = "Largura deve ser maior ou igual a 1")
    private Double width;

    @NotNull(message = "O ID da unidade é obrigatório", groups = OnCreate.class)
    private Long unitId;

    @NotNull(message = "O ID do produto é obrigatório", groups = OnCreate.class)
    private Long productId;

    public String getBatchCode() {
        return batchCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Double getHeight() {
        return height;
    }

    public Double getLength() {
        return length;
    }

    public Double getWidth() {
        return width;
    }

    public void setBatchCode(String batchId) {
        this.batchCode = batchId;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
