package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.AirplaneManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneManufacturerRepository extends JpaRepository<AirplaneManufacturer, Long> {
    AirplaneManufacturer getAirplaneManufacturerByName(String name);
}
