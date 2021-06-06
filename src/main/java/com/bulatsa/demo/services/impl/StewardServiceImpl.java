package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.StewardSeedDto;
import com.bulatsa.demo.models.entities.Steward;
import com.bulatsa.demo.repositories.StewardRepository;
import com.bulatsa.demo.services.AirOperatorService;
import com.bulatsa.demo.services.StewardService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class StewardServiceImpl implements StewardService {
    private final StewardRepository stewardRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;
    private final AirOperatorService airOperatorService;

    @Autowired
    public StewardServiceImpl(StewardRepository stewardRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper, AirOperatorService airOperatorService) {
        this.stewardRepository = stewardRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
        this.airOperatorService = airOperatorService;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.STEWARDS_URL);
    }

    @Override
    public void importStewards() throws IOException {
        StewardSeedDto[] stewardSeedDtos = this.gson.fromJson(this.readFromFileContent(), StewardSeedDto[].class);

        for (StewardSeedDto stewardSeedDto : stewardSeedDtos) {
            Steward steward = this.getById(stewardSeedDto.getId());

            if (steward == null) {
                steward = this.modelMapper.map(stewardSeedDto, Steward.class);

                steward.setAirOperator(this.airOperatorService.getByName(stewardSeedDto.getAirOperatorName()));

                this.stewardRepository.saveAndFlush(steward);
            }
        }
    }

    @Override
    public Steward getById(Long stewardId) {
        return this.stewardRepository.getStewardById(stewardId);
    }

    @Override
    public Set<Steward> getAllByIds(Set<Long> stewardsIds) {
        return this.stewardRepository.getAllByIdIsIn(stewardsIds);
    }

    @Override
    public boolean areImported() {
        return this.stewardRepository.count() > 0;
    }
}
