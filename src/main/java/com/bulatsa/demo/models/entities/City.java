package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @ManyToOne(optional = false, targetEntity = Country.class)
    @JoinColumn(name = "country_name", nullable = false, referencedColumnName = "name")
    private Country country;

    public City() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
