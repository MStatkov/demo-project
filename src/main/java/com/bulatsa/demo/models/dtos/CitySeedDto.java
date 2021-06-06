package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class CitySeedDto {
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private String countryName;

    public CitySeedDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
