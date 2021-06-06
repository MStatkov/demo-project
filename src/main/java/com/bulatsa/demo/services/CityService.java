package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.City;

import java.io.IOException;

public interface CityService {
    String readFromFileContent() throws IOException;

    void importCities() throws IOException;

    City getById(Long cityId);

    boolean areImported();
}
