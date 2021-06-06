package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.AirplaneSeedDto;
import com.bulatsa.demo.models.entities.Airplane;
import com.bulatsa.demo.repositories.AirplaneRepository;
import com.bulatsa.demo.services.AirOperatorService;
import com.bulatsa.demo.services.AirplaneManufacturerService;
import com.bulatsa.demo.services.AirplaneService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;
    private final AirplaneManufacturerService airplaneManufacturerService;
    private final AirOperatorService airOperatorService;

    @Autowired
    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper, AirplaneManufacturerService airplaneManufacturerService, AirOperatorService airOperatorService) {
        this.airplaneRepository = airplaneRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
        this.airplaneManufacturerService = airplaneManufacturerService;
        this.airOperatorService = airOperatorService;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.AIRPLANES_URL);
    }

    @Override
    public void importAirplanes() throws IOException {
        AirplaneSeedDto[] airplaneSeedDtos = this.gson.fromJson(this.readFromFileContent(), AirplaneSeedDto[].class);

        for (AirplaneSeedDto airplaneSeedDto : airplaneSeedDtos) {
            Airplane airplane = this.getByTransponderNumber(airplaneSeedDto.getTransponderNumber());

            if (airplane == null) {
                airplane = this.modelMapper.map(airplaneSeedDto, Airplane.class);

                airplane.setAirplaneManufacturer(this.airplaneManufacturerService.getByName(airplaneSeedDto.getAirplaneManufacturerName()));
                airplane.setAirOperator(this.airOperatorService.getByName(airplaneSeedDto.getAirplaneOperatorName()));

                this.airplaneRepository.saveAndFlush(airplane);
            }
        }
    }

    @Override
    public Airplane getByTransponderNumber(String transponderNumber) {
        return this.airplaneRepository.getAirplaneByTransponderNumber(transponderNumber);
    }

    @Override
    public boolean areImported() {
        return this.airplaneRepository.count() > 0;
    }
}
