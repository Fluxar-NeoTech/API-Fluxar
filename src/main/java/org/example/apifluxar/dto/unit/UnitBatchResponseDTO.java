package org.example.apifluxar.dto.unit;

import org.example.apifluxar.dto.industry.IndustryResponseDTO;

public class UnitBatchResponseDTO {
    private String name;
    private String postalCode;
    private String street;
    private String city;
    private String state;
    private String number;
    private String neighborhood;

    public UnitBatchResponseDTO(String name, String postalCode, String street, String city, String state, String number, String neighborhood) {
        this.name = name;
        this.postalCode = postalCode;
        this.street = street;
        this.city = city;
        this.state = state;
        this.number = number;
        this.neighborhood = neighborhood;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
