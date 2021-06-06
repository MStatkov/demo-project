package com.bulatsa.demo.repositories;

import com.bulatsa.demo.models.entities.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {
    Pilot getPilotByATPLNumber(String atplNumber);

    Pilot getPilotById(Long pilotId);

    Set<Pilot> getAllByATPLNumberIsIn(Set<String> atplNumbers);

    Set<Pilot> getAllByIdIsIn(Set<Long> pilotsIds);
}
