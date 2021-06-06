package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "stewards")
public class Steward implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 40, nullable = false)
    private String lastName;

    @ManyToOne(optional = false, targetEntity = AirOperator.class)
    @JoinColumn(name = "air_operator_name", nullable = false, referencedColumnName = "name")
    private AirOperator airOperator;

    @ManyToMany(targetEntity = Flight.class, mappedBy = "stewards")
    private Set<Flight> flights;

    public Steward() {
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

    public AirOperator getAirOperator() {
        return this.airOperator;
    }

    public void setAirOperator(AirOperator airOperator) {
        this.airOperator = airOperator;
    }

    public Set<Flight> getFlights() {
        return this.flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
