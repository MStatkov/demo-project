package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "passengers", uniqueConstraints = @UniqueConstraint(columnNames = {"nationality", "passport_number"}))
public class Passenger implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nationality", nullable = false, length = 60)
    private String nationality;

    @Column(name = "passport_number", nullable = false, length = 30)
    private String passportNumber;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 40, nullable = false)
    private String lastName;

    @ManyToMany(targetEntity = Flight.class, mappedBy = "passengers")
    private Set<Flight> flights;

    public Passenger() {
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

    public Set<Flight> getFlights() {
        return this.flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
