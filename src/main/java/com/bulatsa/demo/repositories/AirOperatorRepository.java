package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.AirOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirOperatorRepository extends JpaRepository<AirOperator, Long> {
    AirOperator getAirOperatorByName(String name);
}
