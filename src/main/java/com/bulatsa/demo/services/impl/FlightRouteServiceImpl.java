package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.FlightRouteSeedDto;
import com.bulatsa.demo.models.entities.FlightRoute;
import com.bulatsa.demo.repositories.FlightRouteRepository;
import com.bulatsa.demo.services.AirportService;
import com.bulatsa.demo.services.FlightRouteService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FlightRouteServiceImpl implements FlightRouteService {
    private final FlightRouteRepository flightRouteRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;
    private final AirportService airportService;

    @Autowired
    public FlightRouteServiceImpl(FlightRouteRepository flightRouteRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper, AirportService airportService) {
        this.flightRouteRepository = flightRouteRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
        this.airportService = airportService;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.FLIGHT_ROUTES_URL);
    }

    @Override
    public void importFlightRoutes() throws IOException {
        FlightRouteSeedDto[] flightRouteSeedDtos = this.gson.fromJson(this.readFromFileContent(), FlightRouteSeedDto[].class);

        for (FlightRouteSeedDto flightRouteSeedDto : flightRouteSeedDtos) {
            FlightRoute flightRoute = this.getById(flightRouteSeedDto.getId());

            if (flightRoute == null) {
                flightRoute = this.modelMapper.map(flightRouteSeedDto, FlightRoute.class);

                flightRoute.setFromAirportAbbreviationCode(this.airportService.getByAbbreviationCode(flightRouteSeedDto.getAirportFromAirportAbbreviationCode()));
                flightRoute.setToAirportAbbreviationCode(this.airportService.getByAbbreviationCode(flightRouteSeedDto.getAirportToAirportAbbreviationCode()));

                this.flightRouteRepository.saveAndFlush(flightRoute);
            }
        }
    }

    @Override
    public FlightRoute getById(Long flightRouteId) {
        return this.flightRouteRepository.getFlightRouteById(flightRouteId);
    }

    @Override
    public boolean areImported() {
        return this.flightRouteRepository.count() > 0;
    }
}
