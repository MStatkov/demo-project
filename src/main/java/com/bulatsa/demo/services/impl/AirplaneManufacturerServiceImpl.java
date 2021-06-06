package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.AirplaneManufacturerSeedDto;
import com.bulatsa.demo.models.entities.AirplaneManufacturer;
import com.bulatsa.demo.repositories.AirplaneManufacturerRepository;
import com.bulatsa.demo.services.AirplaneManufacturerService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AirplaneManufacturerServiceImpl implements AirplaneManufacturerService {
    private final AirplaneManufacturerRepository airplaneManufacturerRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AirplaneManufacturerServiceImpl(AirplaneManufacturerRepository airplaneManufacturerRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper) {
        this.airplaneManufacturerRepository = airplaneManufacturerRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.AIR_PLANE_MANUFACTURERS_URL);
    }

    @Override
    public void importAirplaneManufacturers() throws IOException {
        AirplaneManufacturerSeedDto[] airplaneManufacturerSeedDtos = this.gson.fromJson(this.readFromFileContent(), AirplaneManufacturerSeedDto[].class);

        for (AirplaneManufacturerSeedDto airplaneManufacturerSeedDto : airplaneManufacturerSeedDtos) {
            AirplaneManufacturer airplaneManufacturer = this.getByName(airplaneManufacturerSeedDto.getName());

            if (airplaneManufacturer == null) {
                airplaneManufacturer = this.modelMapper.map(airplaneManufacturerSeedDto, AirplaneManufacturer.class);

                this.airplaneManufacturerRepository.saveAndFlush(airplaneManufacturer);
            }
        }
    }

    @Override
    public AirplaneManufacturer getByName(String name) {
        return this.airplaneManufacturerRepository.getAirplaneManufacturerByName(name);
    }

    @Override
    public boolean areImported() {
        return this.airplaneManufacturerRepository.count() > 0;
    }
}
