package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, targetEntity = Airplane.class)
    @JoinColumn(name = "airplane_transponder_number", nullable = false, referencedColumnName = "transponder_number")
    private Airplane airplane;

    @ManyToOne(optional = false, targetEntity = FlightRoute.class)
    @JoinColumn(name = "fligt_route_id", nullable = false, referencedColumnName = "id")
    private FlightRoute flightRoute;

    @ManyToOne(optional = false, targetEntity = AirOperator.class)
    @JoinColumn(name = "air_operator_name", nullable = false, referencedColumnName = "name")
    private AirOperator airOperator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "flights_pilots",
            joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pilot_id", referencedColumnName = "id"))
    private Set<Pilot> pilots;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "flights_stewards",
            joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "steward_id", referencedColumnName = "id"))
    private Set<Steward> stewards;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "flights_passengers",
            joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    private Set<Passenger> passengers;

    @Column(name = "airplane_taking_off")
    private LocalDateTime airplaneTakingOff;

    @Column(name = "airplane_landing")
    private LocalDateTime airplaneLanding;

    public Flight() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airplane getAirplane() {
        return this.airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public FlightRoute getFlightRoute() {
        return this.flightRoute;
    }

    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
    }

    public AirOperator getAirOperator() {
        return this.airOperator;
    }

    public void setAirOperator(AirOperator airOperator) {
        this.airOperator = airOperator;
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

    public Set<Passenger> getPassengers() {
        return this.passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public LocalDateTime getAirplaneTakingOff() {
        return this.airplaneTakingOff;
    }

    public void setAirplaneTakingOff(LocalDateTime airplaneTakingOff) {
        this.airplaneTakingOff = airplaneTakingOff;
    }

    public LocalDateTime getAirplaneLanding() {
        return this.airplaneLanding;
    }

    public void setAirplaneLanding(LocalDateTime airplaneLanding) {
        this.airplaneLanding = airplaneLanding;
    }
}
