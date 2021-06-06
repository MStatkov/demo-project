package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight getFlightById(Long flightId);

    Set<Flight> getAllByAirplaneTakingOffIsNull();

    Set<Flight> getAllByAirplaneTakingOffIsNotNullAndAirplaneLandingIsNull();

    Set<Flight> getAllByAirplaneTakingOffIsNotNullAndAirplaneLandingIsNotNull();
}
