package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "flight_routes")
public class FlightRoute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, targetEntity = Airport.class)
    @JoinColumn(name = "from_airport_abbreviation_code", nullable = false, referencedColumnName = "abbreviation_code")
    private Airport fromAirportAbbreviationCode;

    @ManyToOne(optional = false, targetEntity = Airport.class)
    @JoinColumn(name = "to_airport_abbreviation_code", nullable = false, referencedColumnName = "abbreviation_code")
    private Airport toAirportAbbreviationCode;

    public FlightRoute() {
    }

    public Airport getFromAirportAbbreviationCode() {
        return this.fromAirportAbbreviationCode;
    }

    public void setFromAirportAbbreviationCode(Airport fromAirportAbbreviationCode) {
        this.fromAirportAbbreviationCode = fromAirportAbbreviationCode;
    }

    public Airport getToAirportAbbreviationCode() {
        return this.toAirportAbbreviationCode;
    }

    public void setToAirportAbbreviationCode(Airport toAirportAbbreviationCode) {
        this.toAirportAbbreviationCode = toAirportAbbreviationCode;
    }
}
