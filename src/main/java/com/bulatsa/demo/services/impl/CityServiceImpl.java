package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.CitySeedDto;
import com.bulatsa.demo.models.entities.City;
import com.bulatsa.demo.repositories.CityRepository;
import com.bulatsa.demo.services.CityService;
import com.bulatsa.demo.services.CountryService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;
    private final CountryService countryService;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper, CountryService countryService) {
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
        this.countryService = countryService;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.CITIES_URL);
    }

    @Override
    public void importCities() throws IOException {
        CitySeedDto[] citySeedDtos = this.gson.fromJson(this.readFromFileContent(), CitySeedDto[].class);

        for (CitySeedDto citySeedDto : citySeedDtos) {
            City city = this.getById(citySeedDto.getId());

            if (city == null) {
                city = this.modelMapper.map(citySeedDto, City.class);

                city.setCountry(this.countryService.getByName(citySeedDto.getCountryName()));

                this.cityRepository.saveAndFlush(city);
            }
        }
    }

    @Override
    public City getById(Long cityId) {
        return this.cityRepository.getCityById(cityId);
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }
}
