package org.example.apifluxar.dto.unit;

public class UnitDimensionsResponseDTO {
    private Integer widthDimension;
    private Integer heightDimension;
    private Integer lengthDimension;

    public UnitDimensionsResponseDTO(Integer widthDimension, Integer heightDimension, Integer lengthDimension) {
        this.widthDimension = widthDimension;
        this.heightDimension = heightDimension;
        this.lengthDimension = lengthDimension;
    }

    public Integer getWidthDimension() {
        return widthDimension;
    }

    public void setWidthDimension(Integer widthDimension) {
        this.widthDimension = widthDimension;
    }

    public Integer getHeightDimension() {
        return heightDimension;
    }

    public void setHeightDimension(Integer heightDimension) {
        this.heightDimension = heightDimension;
    }

    public Integer getLengthDimension() {
        return lengthDimension;
    }

    public void setLengthDimension(Integer lengthDimension) {
        this.lengthDimension = lengthDimension;
    }
}
