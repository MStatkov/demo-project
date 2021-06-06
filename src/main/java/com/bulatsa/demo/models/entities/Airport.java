package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "airports")
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "abbreviation_code", length = 4, nullable = false, unique = true)
    private String abbreviationCode;

    @ManyToOne(optional = false, targetEntity = City.class)
    @JoinColumn(name = "city_id", nullable = false, referencedColumnName = "id")
    private City city;

    public Airport() {
    }

    public String getAbbreviationCode() {
        return this.abbreviationCode;
    }

    public void setAbbreviationCode(String abbreviationCode) {
        this.abbreviationCode = abbreviationCode;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
