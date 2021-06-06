package com.bulatsa.demo.services;

import com.bulatsa.demo.models.entities.Steward;

import java.io.IOException;
import java.util.Set;

public interface StewardService {
    String readFromFileContent() throws IOException;

    void importStewards() throws IOException;

    Steward getById(Long stewardId);

    Set<Steward> getAllByIds(Set<Long> stewardsIds);

    boolean areImported();
}
