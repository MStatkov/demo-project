package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.Country;

import java.io.IOException;

public interface CountryService {
    String readFromFileContent() throws IOException;

    void importCountries() throws IOException;

    Country getByName(String name);

    boolean areImported();
}
