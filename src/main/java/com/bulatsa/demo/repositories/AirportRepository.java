package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport getAirportByAbbreviationCode(String abbreviationCode);
}
