package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.Passenger;

import java.io.IOException;
import java.util.Set;

public interface PassengerService {
    String readFromFileContent() throws IOException;

    void importPassengers() throws IOException;

    Passenger getByNationalityAndPassportNumber(String nationality, String passportNumber);

    Passenger getById(Long passengerId);

    Set<Passenger> getAllByNationalitiesAndPassportNumbers(Set<String> nationalities, Set<String> passportNumbers);

    Set<Passenger> getAllByIds(Set<Long> passengersIds);

    boolean areImported();
}
