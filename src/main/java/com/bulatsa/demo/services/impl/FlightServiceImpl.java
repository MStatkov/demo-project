package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.FlightSeedDto;
import com.bulatsa.demo.models.entities.*;
import com.bulatsa.demo.repositories.FlightRepository;
import com.bulatsa.demo.services.*;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;
    private final AirplaneService airplaneService;
    private final FlightRouteService flightRouteService;
    private final AirOperatorService airOperatorService;
    private final PilotsService pilotsService;
    private final StewardService stewardService;
    private final PassengerService passengerService;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper, AirplaneService airplaneService, FlightRouteService flightRouteService, AirOperatorService airOperatorService, PilotsService pilotsService, StewardService stewardService, PassengerService passengerService) {
        this.flightRepository = flightRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
        this.airplaneService = airplaneService;
        this.flightRouteService = flightRouteService;
        this.airOperatorService = airOperatorService;
        this.pilotsService = pilotsService;
        this.stewardService = stewardService;
        this.passengerService = passengerService;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.FLIGHTS_URL);
    }

    @Override
    public void importFlights() throws IOException {
        FlightSeedDto[] flightSeedDtos = this.gson.fromJson(this.readFromFileContent(), FlightSeedDto[].class);

        for (FlightSeedDto flightSeedDto : flightSeedDtos) {
            Flight flight = this.getById(flightSeedDto.getId());

            if (flight == null) {
                flight = this.modelMapper.map(flightSeedDto, Flight.class);

                flight.setAirplane(this.airplaneService.getByTransponderNumber(flightSeedDto.getAirplaneTransponderNumber()));
                flight.setFlightRoute(this.flightRouteService.getById(flightSeedDto.getFlightRouteId()));
                flight.setAirOperator(this.airOperatorService.getByName(flightSeedDto.getAirOperatorName()));
                flight.setPilots(this.pilotsService.getAllByIds(flightSeedDto.getPilotsId()));
                flight.setStewards(this.stewardService.getAllByIds(flightSeedDto.getStewardsId()));
                flight.setPassengers(this.passengerService.getAllByIds(flightSeedDto.getPassengersId()));

                this.flightRepository.saveAndFlush(flight);
            }
        }
    }

    @Override
    public Flight getById(Long flightId) {
        return this.flightRepository.getFlightById(flightId);
    }

    @Override
    public String getAllFlightsStillNotTakenOff() {
        Set<Flight> flights = this.flightRepository.getAllByAirplaneTakingOffIsNull();

        return responseBuilder(flights);
    }

    @Override
    public String getAllFlightsStillFlying() {
        Set<Flight> flights = this.flightRepository.getAllByAirplaneTakingOffIsNotNullAndAirplaneLandingIsNull();

        return responseBuilder(flights);
    }

    @Override
    public String getAllFlightsAlreadyLanded() {
        Set<Flight> flights = this.flightRepository.getAllByAirplaneTakingOffIsNotNullAndAirplaneLandingIsNotNull();

        return responseBuilder(flights);
    }

    @Override
    public boolean areImported() {
        return this.flightRepository.count() > 0;
    }

    private String responseBuilder(Set<Flight> flights) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Flight flight : flights) {
            Long flightId = flight.getId();
            Airplane airplane = flight.getAirplane();
            FlightRoute flightRoute = flight.getFlightRoute();
            AirOperator airOperator = flight.getAirOperator();
            Set<Pilot> pilots = flight.getPilots();
            Set<Steward> stewards = flight.getStewards();
            Set<Passenger> passengers = flight.getPassengers();
            LocalDateTime takingOffDateTime = flight.getAirplaneTakingOff();
            LocalDateTime landingDateTime = flight.getAirplaneLanding();
            String takingOff = takingOffDateTime == null ? "?????????????????? ?????? ???? ?? ??????????????!" : "?????????????????? ?? ?????????????? ????: " + takingOffDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy ??., HH:mm:ss ??."));
            String landing = landingDateTime == null ? "?????????????????? ?????? ???? ?? ????????????!" : "?????????????????? ???? ?? ???????????????? ????: " + landingDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy ??., HH:mm:ss ??."));

            AirplaneManufacturer airplaneManufacturer = airplane.getAirplaneManufacturer();
            Airport startingAirport = flightRoute.getFromAirportAbbreviationCode();
            Airport endingAirport = flightRoute.getToAirportAbbreviationCode();
            City startingCity = startingAirport.getCity();
            City endingCity = endingAirport.getCity();
            Country startingCountry = startingCity.getCountry();
            Country endingCountry = endingCity.getCountry();

            String airOperatorName = airOperator.getName();
            String airplaneDetails = airplaneManufacturer.getName() + " " + airplane.getAirplaneModel();
            String flightFrom = String.format("?????????????? ?? ???? ???????????? %s, ????. %s, ??????????????: %s.",
                    startingAirport.getAbbreviationCode(),
                    startingCity.getName(),
                    startingCountry.getName());
            String flightTo = String.format("?????????????? ?? ???? ???????????? %s, ????. %s, ??????????????: %s.",
                    endingAirport.getAbbreviationCode(),
                    endingCity.getName(),
                    endingCountry.getName());

            StringBuilder passengerDetails = new StringBuilder();
            passengerDetails.append("??????????????:").append(System.lineSeparator());
            for (Passenger passenger : passengers) {
                String passengerFirstName = passenger.getFirstName();
                String passengerLastName = passenger.getLastName();
                String passengerNationality = passenger.getNationality();
                String passengerPassportNumber = passenger.getPassportNumber();

                String passengerInfo = String.format("* ???????????? %s %s ?? %s ?????????????????????? ?? ?????????????? ?? ?????????? %s.",
                        passengerFirstName, passengerLastName, passengerNationality, passengerPassportNumber);

                passengerDetails.append(passengerInfo).append(System.lineSeparator());
            }
            String passengerData = passengerDetails.toString();

            StringBuilder pilotsDetails = new StringBuilder();
            pilotsDetails.append("????????????:").append(System.lineSeparator());
            for (Pilot pilot : pilots) {
                String pilotLicense = pilot.getATPLNumber();
                String pilotFirstName = pilot.getFirstName();
                String pilotLastName = pilot.getLastName();

                String pilotInfo = String.format("# ?????????? %s %s, ?? ???????????????? ??????????: %s.",
                        pilotFirstName, pilotLastName, pilotLicense);
                pilotsDetails.append(pilotInfo).append(System.lineSeparator());
            }
            String pilotData = pilotsDetails.toString();

            StringBuilder stewardDetails = new StringBuilder();
            stewardDetails.append("??????????????:").append(System.lineSeparator());
            for (Steward steward : stewards) {
                String stewardFirstName = steward.getFirstName();
                String stewardLastName = steward.getLastName();

                String stewardInfo = String.format("$ ???????????? %s %s.",
                        stewardFirstName, stewardLastName);
                stewardDetails.append(stewardInfo).append(System.lineSeparator());
            }
            String stewardData = stewardDetails.toString();

            String currentFlightData = String.format("?????????? ??? %d, ???????????????????? ?????? ?????????????? %s ???? ???????????????????????? %s ?? ?????? ???????????????? ????????????????????????????:%n???????????? ???? ???????????? (????????????????): %s %s%n???????????? ???? ???????????? (??????????????????????): %s %s%n%s%n%s%n%s%n",
                    flightId, airplaneDetails, airOperatorName, flightFrom, takingOff, flightTo, landing, pilotData, stewardData, passengerData);

            stringBuilder.append(currentFlightData);
        }

        return stringBuilder.toString();
    }
}
