package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class AirportSeedDto {
    @Expose
    private String airportAbbreviationCode;
    @Expose
    private Long cityId;

    public AirportSeedDto() {
    }

    public String getAirportAbbreviationCode() {
        return this.airportAbbreviationCode;
    }

    public void setAirportAbbreviationCode(String airportAbbreviationCode) {
        this.airportAbbreviationCode = airportAbbreviationCode;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
