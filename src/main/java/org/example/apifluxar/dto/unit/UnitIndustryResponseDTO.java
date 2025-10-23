package org.example.apifluxar.dto.unit;

public class UnitIndustryResponseDTO {
    private Long id;
    private String name;
    private String postalCode;
    private String street;
    private String city;
    private String state;
    private String number;
    private String neighborhood;
    private String email;
    private Long availability;

    public UnitIndustryResponseDTO(Long id, String name, String postalCode, String street, String city, String state, String number, String neighborhood, String email, Long availability) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.street = street;
        this.city = city;
        this.state = state;
        this.number = number;
        this.neighborhood = neighborhood;
        this.email = email;
        this.availability = availability;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvailability() {
        return availability;
    }

    public void setAvailability(Long availability) {
        this.availability = availability;
    }
}
