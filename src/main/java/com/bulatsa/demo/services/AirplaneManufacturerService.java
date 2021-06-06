package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.AirplaneManufacturer;

import java.io.IOException;

public interface AirplaneManufacturerService {
    String readFromFileContent() throws IOException;

    void importAirplaneManufacturers() throws IOException;

    AirplaneManufacturer getByName(String name);

    boolean areImported();
}
