package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.Set;

public class FlightSeedDto {
    @Expose
    private Long id;
    @Expose
    private LocalDateTime airplaneTakingOff;
    @Expose
    private LocalDateTime airplaneLanding;

    @Expose
    private String airplaneTransponderNumber;
    @Expose
    private Long flightRouteId;
    @Expose
    private String airOperatorName;
    @Expose
    private Set<Long> pilotsId;
    @Expose
    private Set<Long> stewardsId;
    @Expose
    private Set<Long> passengersId;

    public FlightSeedDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAirplaneTransponderNumber() {
        return this.airplaneTransponderNumber;
    }

    public void setAirplaneTransponderNumber(String airplaneTransponderNumber) {
        this.airplaneTransponderNumber = airplaneTransponderNumber;
    }

    public Long getFlightRouteId() {
        return this.flightRouteId;
    }

    public void setFlightRouteId(Long flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    public String getAirOperatorName() {
        return this.airOperatorName;
    }

    public void setAirOperatorName(String airOperatorName) {
        this.airOperatorName = airOperatorName;
    }

    public Set<Long> getPilotsId() {
        return this.pilotsId;
    }

    public void setPilotsId(Set<Long> pilotsId) {
        this.pilotsId = pilotsId;
    }

    public Set<Long> getStewardsId() {
        return this.stewardsId;
    }

    public void setStewardsId(Set<Long> stewardsId) {
        this.stewardsId = stewardsId;
    }

    public Set<Long> getPassengersId() {
        return this.passengersId;
    }

    public void setPassengersId(Set<Long> passengersId) {
        this.passengersId = passengersId;
    }
}
