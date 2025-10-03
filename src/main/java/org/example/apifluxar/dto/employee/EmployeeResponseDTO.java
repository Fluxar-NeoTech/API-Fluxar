package org.example.apifluxar.dto.employee;

import org.example.apifluxar.dto.plan.PlanResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;

public class EmployeeResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String profilePhoto;
    private SectorResponseDTO sector;
    private UnitResponseDTO unit;
    private Double maxCapacity;
    private PlanResponseDTO plan;

    public EmployeeResponseDTO(String firstName, String lastName, String email, String profilePhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePhoto = profilePhoto;
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

    public void setPlan(PlanResponseDTO plan) {
        this.plan = plan;
    }

    public PlanResponseDTO getPlan() {
        return plan;
    }
}
