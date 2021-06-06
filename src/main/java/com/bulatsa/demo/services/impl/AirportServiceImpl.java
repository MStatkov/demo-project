package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.AirportSeedDto;
import com.bulatsa.demo.models.entities.Airport;
import com.bulatsa.demo.repositories.AirportRepository;
import com.bulatsa.demo.services.AirportService;
import com.bulatsa.demo.services.CityService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;
    private final CityService cityService;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper, CityService cityService) {
        this.airportRepository = airportRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.AIRPORTS_URL);
    }

    @Override
    public void importAirports() throws IOException {
        AirportSeedDto[] airportSeedDtos = this.gson.fromJson(this.readFromFileContent(), AirportSeedDto[].class);

        for (AirportSeedDto airportSeedDto : airportSeedDtos) {
            Airport airport = this.getByAbbreviationCode(airportSeedDto.getAirportAbbreviationCode());

            if (airport == null) {
                airport = this.modelMapper.map(airportSeedDto, Airport.class);

                airport.setCity(this.cityService.getById(airportSeedDto.getCityId()));

                this.airportRepository.saveAndFlush(airport);
            }
        }
    }

    @Override
    public Airport getByAbbreviationCode(String abbreviationCode) {
        return this.airportRepository.getAirportByAbbreviationCode(abbreviationCode);
    }

    @Override
    public boolean areImported() {
        return this.airportRepository.count() > 0;
    }
}
