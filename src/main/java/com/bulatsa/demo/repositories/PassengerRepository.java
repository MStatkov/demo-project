package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger getPassengerByNationalityAndPassportNumber(String nationality, String passportNumber);

    Passenger getPassengerById(Long passengerId);

    Set<Passenger> getAllByNationalityInAndPassportNumberIn(Set<String> nationalities, Set<String> passportNumbers);

    Set<Passenger> getAllByIdIsIn(Set<Long> passengersIds);
}
