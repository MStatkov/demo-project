package com.bulatsa.demo.web.controllers;

import com.bulatsa.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class ImportController extends BaseController {
    private final AirOperatorService airOperatorService;
    private final AirplaneManufacturerService airplaneManufacturerService;
    private final AirplaneService airplaneService;
    private final AirportService airportService;
    private final CityService cityService;
    private final CountryService countryService;
    private final FlightRouteService flightRouteService;
    private final FlightService flightService;
    private final PassengerService passengerService;
    private final PilotsService pilotsService;
    private final StewardService stewardService;

    @Autowired
    public ImportController(AirOperatorService airOperatorService, AirplaneManufacturerService airplaneManufacturerService, AirplaneService airplaneService, AirportService airportService, CityService cityService, CountryService countryService, FlightRouteService flightRouteService, FlightService flightService, PassengerService passengerService, PilotsService pilotsService, StewardService stewardService) {
        this.airOperatorService = airOperatorService;
        this.airplaneManufacturerService = airplaneManufacturerService;
        this.airplaneService = airplaneService;
        this.airportService = airportService;
        this.cityService = cityService;
        this.countryService = countryService;
        this.flightRouteService = flightRouteService;
        this.flightService = flightService;
        this.passengerService = passengerService;
        this.pilotsService = pilotsService;
        this.stewardService = stewardService;
    }

    @GetMapping("/import")
    public ModelAndView importFiles() throws IOException {
        this.countryService.importCountries();
        this.cityService.importCities();
        this.airportService.importAirports();
        this.flightRouteService.importFlightRoutes();
        this.airplaneManufacturerService.importAirplaneManufacturers();
        this.airOperatorService.importAirOperators();
        this.airplaneService.importAirplanes();
        this.pilotsService.importPilots();
        this.stewardService.importStewards();
        this.passengerService.importPassengers();
        this.flightService.importFlights();

        return super.redirect("/");
    }
}
