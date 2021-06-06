package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.Pilot;

import java.io.IOException;
import java.util.Set;

public interface PilotsService {
    String readFromFileContent() throws IOException;

    void importPilots() throws IOException;

    Pilot getByATPLNumber(String atplNumber);

    Pilot getById(Long pilotId);

    Set<Pilot> getAllByATPLNumbers(Set<String> atplNumbers);

    Set<Pilot> getAllByIds(Set<Long> pilotsIds);

    boolean areImported();
}
