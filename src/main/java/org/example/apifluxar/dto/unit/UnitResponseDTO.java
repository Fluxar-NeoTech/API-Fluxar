package org.example.apifluxar.dto.unit;

import org.example.apifluxar.dto.industry.IndustryResponseDTO;

public class UnitResponseDTO {
    private Long id;
    private String name;
    private String postalCode;
    private String street;
    private String city;
    private String state;
    private String number;
    private String neighborhood;
    private String email;
    private Integer availabilityUnit;
    private IndustryResponseDTO industry;

    public UnitResponseDTO() {
    }

    public UnitResponseDTO(Long id, String name, String postalCode, String street, String city, String state, String number, String neighborhood, String email, Integer avaliabilityUnit, IndustryResponseDTO industry) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.street = street;
        this.city = city;
        this.state = state;
        this.number = number;
        this.neighborhood = neighborhood;
        this.email = email;
        this.availabilityUnit = avaliabilityUnit;
        this.industry = industry;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public IndustryResponseDTO getIndustry() {
        return industry;
    }

    public void setIndustry(IndustryResponseDTO industry) {
        this.industry = industry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAvailabilityUnit() {
        return availabilityUnit;
    }

    public void setAvailabilityUnit(Integer availabilityUnit) {
        this.availabilityUnit = availabilityUnit;
    }
}
