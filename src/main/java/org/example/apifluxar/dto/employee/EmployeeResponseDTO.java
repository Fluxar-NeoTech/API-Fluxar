package org.example.apifluxar.dto.employee;

import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;

public class EmployeeResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Character role;
    private String profilePhoto;
    private SectorResponseDTO sector;
    private UnitResponseDTO unit;
    private Double maxCapacity;

    public EmployeeResponseDTO(Long id, String firstName, String lastName, String email, Character role, String profilePhoto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.profilePhoto = profilePhoto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public SectorResponseDTO getSector() {
        return sector;
    }
    public void setSector(SectorResponseDTO sector) {
        this.sector = sector;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getRole() {
        return role;
    }

    public void setRole(Character role) {
        this.role = role;
    }

    public UnitResponseDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitResponseDTO unit) {
        this.unit = unit;
    }

    public Double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
