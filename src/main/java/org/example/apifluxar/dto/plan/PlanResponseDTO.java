package org.example.apifluxar.dto.plan;

public class PlanResponseDTO {
    private String name;
    private Integer monthsDuration;

    public PlanResponseDTO(String name, Integer monthsDuration) {
        this.name = name;
        this.monthsDuration = monthsDuration;
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
