package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "air_operators")
public class AirOperator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 35, nullable = false, unique = true)
    private String name;

    @OneToMany(targetEntity = Airplane.class, mappedBy = "airOperator")
    private Set<Airplane> airplanes;

    @OneToMany(targetEntity = Pilot.class, mappedBy = "airOperator")
    private Set<Pilot> pilots;

    @OneToMany(targetEntity = Steward.class, mappedBy = "airOperator")
    private Set<Steward> stewards;

    public AirOperator() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Airplane> getAirplanes() {
        return this.airplanes;
    }

    public void setAirplanes(Set<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public Set<Pilot> getPilots() {
        return this.pilots;
    }

    public void setPilots(Set<Pilot> pilots) {
        this.pilots = pilots;
    }

    public Set<Steward> getStewards() {
        return this.stewards;
    }

    public void setStewards(Set<Steward> stewards) {
        this.stewards = stewards;
    }
}
