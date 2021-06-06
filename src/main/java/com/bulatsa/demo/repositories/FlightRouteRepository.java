package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.FlightRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRouteRepository extends JpaRepository<FlightRoute, Long> {
    FlightRoute getFlightRouteById(Long flightRouteId);
}
