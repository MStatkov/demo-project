package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.PilotSeedDto;
import com.bulatsa.demo.models.entities.Pilot;
import com.bulatsa.demo.repositories.PilotRepository;
import com.bulatsa.demo.services.AirOperatorService;
import com.bulatsa.demo.services.PilotsService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class PilotServiceImpl implements PilotsService {
    private final PilotRepository pilotRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;
    private final AirOperatorService airOperatorService;

    @Autowired
    public PilotServiceImpl(PilotRepository pilotRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper, AirOperatorService airOperatorService) {
        this.pilotRepository = pilotRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
        this.airOperatorService = airOperatorService;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.PILOTS_URL);
    }

    @Override
    public void importPilots() throws IOException {
        PilotSeedDto[] pilotSeedDtos = this.gson.fromJson(this.readFromFileContent(), PilotSeedDto[].class);

        for (PilotSeedDto pilotSeedDto : pilotSeedDtos) {
            Pilot pilot = this.getByATPLNumber(pilotSeedDto.getATPLNumber());

            if (pilot == null) {
                pilot = this.modelMapper.map(pilotSeedDto, Pilot.class);

                pilot.setAirOperator(this.airOperatorService.getByName(pilotSeedDto.getAirOperatorName()));

                this.pilotRepository.saveAndFlush(pilot);
            }
        }
    }

    @Override
    public Pilot getByATPLNumber(String atplNumber) {
        return this.pilotRepository.getPilotByATPLNumber(atplNumber);
    }

    @Override
    public Pilot getById(Long pilotId) {
        return this.pilotRepository.getPilotById(pilotId);
    }

    @Override
    public Set<Pilot> getAllByATPLNumbers(Set<String> atplNumbers) {
        return this.pilotRepository.getAllByATPLNumberIsIn(atplNumbers);
    }

    @Override
    public Set<Pilot> getAllByIds(Set<Long> pilotsIds) {
        return this.pilotRepository.getAllByIdIsIn(pilotsIds);
    }

    @Override
    public boolean areImported() {
        return this.pilotRepository.count() > 0;
    }
}
