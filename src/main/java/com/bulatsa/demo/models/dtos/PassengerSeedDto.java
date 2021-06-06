package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class PassengerSeedDto {
    @Expose
    private String nationality;
    @Expose
    private String passportNumber;
    @Expose
    private String firstName;
    @Expose
    private String lastName;

    public PassengerSeedDto() {
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassportNumber() {
        return this.passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
