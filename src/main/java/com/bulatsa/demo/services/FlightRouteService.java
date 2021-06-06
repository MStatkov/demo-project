package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.FlightRoute;

import java.io.IOException;

public interface FlightRouteService {
    String readFromFileContent() throws IOException;

    void importFlightRoutes() throws IOException;

    FlightRoute getById(Long flightRouteId);

    boolean areImported();
}
