package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City getCityById(Long cityId);
}
