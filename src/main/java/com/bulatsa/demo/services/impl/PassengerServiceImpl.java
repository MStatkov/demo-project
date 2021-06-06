package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.PassengerSeedDto;
import com.bulatsa.demo.models.entities.Passenger;
import com.bulatsa.demo.repositories.PassengerRepository;
import com.bulatsa.demo.services.PassengerService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper) {
        this.passengerRepository = passengerRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.PASSENGERS_URL);
    }

    @Override
    public void importPassengers() throws IOException {
        PassengerSeedDto[] passengerSeedDtos = this.gson.fromJson(this.readFromFileContent(), PassengerSeedDto[].class);

        for (PassengerSeedDto passengerSeedDto : passengerSeedDtos) {
            Passenger passenger = this.getByNationalityAndPassportNumber(passengerSeedDto.getNationality(), passengerSeedDto.getPassportNumber());

            if (passenger == null) {
                passenger = this.modelMapper.map(passengerSeedDto, Passenger.class);

                this.passengerRepository.saveAndFlush(passenger);
            }
        }
    }

    @Override
    public Passenger getByNationalityAndPassportNumber(String nationality, String passportNumber) {
        return this.passengerRepository.getPassengerByNationalityAndPassportNumber(nationality, passportNumber);
    }

    @Override
    public Passenger getById(Long passengerId) {
        return this.passengerRepository.getPassengerById(passengerId);
    }

    @Override
    public Set<Passenger> getAllByNationalitiesAndPassportNumbers(Set<String> nationalities, Set<String> passportNumbers) {
        return this.passengerRepository.getAllByNationalityInAndPassportNumberIn(nationalities, passportNumbers);
    }

    @Override
    public Set<Passenger> getAllByIds(Set<Long> passengersIds) {
        return this.passengerRepository.getAllByIdIsIn(passengersIds);
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }
}
