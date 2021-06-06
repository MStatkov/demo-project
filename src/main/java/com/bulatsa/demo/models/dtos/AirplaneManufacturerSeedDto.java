package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class AirplaneManufacturerSeedDto {
    @Expose
    private String name;

    public AirplaneManufacturerSeedDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
