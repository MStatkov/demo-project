package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class StewardSeedDto {
    @Expose
    private Long id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;

    @Expose
    private String airOperatorName;

    public StewardSeedDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAirOperatorName() {
        return this.airOperatorName;
    }

    public void setAirOperatorName(String airOperatorName) {
        this.airOperatorName = airOperatorName;
    }
}
