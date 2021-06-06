package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class PilotSeedDto {
    @Expose
    private String ATPLNumber;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String airOperatorName;

    public PilotSeedDto() {
    }

    public String getATPLNumber() {
        return this.ATPLNumber;
    }

    public void setATPLNumber(String ATPLNumber) {
        this.ATPLNumber = ATPLNumber;
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
