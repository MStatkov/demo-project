package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    Airplane getAirplaneByTransponderNumber(String transponderNumber);
}
