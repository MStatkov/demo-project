package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.Airplane;

import java.io.IOException;

public interface AirplaneService {
    String readFromFileContent() throws IOException;

    void importAirplanes() throws IOException;

    Airplane getByTransponderNumber(String transponderNumber);

    boolean areImported();
}
