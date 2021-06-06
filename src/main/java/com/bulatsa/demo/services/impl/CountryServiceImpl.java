package com.bulatsa.demo.services.impl;

import com.bulatsa.demo.constants.GlobalConstants;
import com.bulatsa.demo.models.dtos.CountrySeedDto;
import com.bulatsa.demo.models.entities.Country;
import com.bulatsa.demo.repositories.CountryRepository;
import com.bulatsa.demo.services.CountryService;
import com.bulatsa.demo.util.FileIOUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, FileIOUtil fileIOUtil, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return this.fileIOUtil.readFileDataGetSimpleString(GlobalConstants.COUNTRIES_URL);
    }

    @Override
    public void importCountries() throws IOException {
        CountrySeedDto[] countrySeedDtos = this.gson.fromJson(this.readFromFileContent(), CountrySeedDto[].class);

        for (CountrySeedDto countrySeedDto : countrySeedDtos) {
            Country country = this.getByName(countrySeedDto.getName());

            if (country == null) {
                country = this.modelMapper.map(countrySeedDto, Country.class);

                this.countryRepository.saveAndFlush(country);
            }
        }
    }

    @Override
    public Country getByName(String name) {
        return this.countryRepository.getCountryByName(name);
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }
}
