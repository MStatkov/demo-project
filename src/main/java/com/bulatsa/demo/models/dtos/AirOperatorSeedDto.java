package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class AirOperatorSeedDto {
    @Expose
    private String name;

    public AirOperatorSeedDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
