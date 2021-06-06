package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.AirOperatorSeedDto;
import com.bulatsa.demo.models.entities.AirOperator;
import com.bulatsa.demo.repositories.AirOperatorRepository;
import com.bulatsa.demo.services.AirOperatorService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AirOperatorServiceImpl implements AirOperatorService {
    private final AirOperatorRepository airOperatorRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AirOperatorServiceImpl(AirOperatorRepository airOperatorRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper) {
        this.airOperatorRepository = airOperatorRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.AIR_OPERATORS_URL);
    }

    @Override
    public void importAirOperators() throws IOException {
        AirOperatorSeedDto[] airOperatorSeedDtos = this.gson.fromJson(this.readFromFileContent(), AirOperatorSeedDto[].class);

        for (AirOperatorSeedDto airOperatorSeedDto : airOperatorSeedDtos) {
            AirOperator airOperator = this.getByName(airOperatorSeedDto.getName());

            if (airOperator == null) {
                airOperator = this.modelMapper.map(airOperatorSeedDto, AirOperator.class);

                this.airOperatorRepository.saveAndFlush(airOperator);
            }
        }
    }

    @Override
    public AirOperator getByName(String name) {
        return this.airOperatorRepository.getAirOperatorByName(name);
    }

    @Override
    public boolean areImported() {
        return this.airOperatorRepository.count() > 0;
    }
}
