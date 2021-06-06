package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.AirOperator;

import java.io.IOException;

public interface AirOperatorService {
    String readFromFileContent() throws IOException;

    void importAirOperators() throws IOException;

    AirOperator getByName(String name);

    boolean areImported();
}
