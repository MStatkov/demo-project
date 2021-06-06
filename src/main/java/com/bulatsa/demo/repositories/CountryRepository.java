package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country getCountryByName(String name);
}
