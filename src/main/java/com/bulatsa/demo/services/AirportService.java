package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.Airport;

import java.io.IOException;

public interface AirportService {
    String readFromFileContent() throws IOException;

    void importAirports() throws IOException;

    Airport getByAbbreviationCode(String abbreviationCode);

    boolean areImported();
}
