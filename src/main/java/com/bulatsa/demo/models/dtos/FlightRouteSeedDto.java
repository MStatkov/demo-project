package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class FlightRouteSeedDto {
    @Expose
    private Long id;
    @Expose
    private String airportFromAirportAbbreviationCode;
    @Expose
    private String airportToAirportAbbreviationCode;

    public FlightRouteSeedDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirportFromAirportAbbreviationCode() {
        return this.airportFromAirportAbbreviationCode;
    }

    public void setAirportFromAirportAbbreviationCode(String airportFromAirportAbbreviationCode) {
        this.airportFromAirportAbbreviationCode = airportFromAirportAbbreviationCode;
    }

    public String getAirportToAirportAbbreviationCode() {
        return this.airportToAirportAbbreviationCode;
    }

    public void setAirportToAirportAbbreviationCode(String airportToAirportAbbreviationCode) {
        this.airportToAirportAbbreviationCode = airportToAirportAbbreviationCode;
    }
}
