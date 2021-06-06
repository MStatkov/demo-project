package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.Flight;

import java.io.IOException;

public interface FlightService {
    String readFromFileContent() throws IOException;

    void importFlights() throws IOException;

    Flight getById(Long flightId);

    String getAllFlightsStillNotTakenOff();

    String getAllFlightsStillFlying();

    String getAllFlightsAlreadyLanded();

    boolean areImported();
}
