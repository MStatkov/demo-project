package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.Steward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StewardRepository extends JpaRepository<Steward, Long> {
    Steward getStewardById(Long stewardId);

    Set<Steward> getAllByIdIsIn(Set<Long> stewardsIds);
}
