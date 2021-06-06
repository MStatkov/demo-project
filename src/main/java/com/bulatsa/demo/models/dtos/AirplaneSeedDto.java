package com.bulatsa.demo.models.dtos;

import com.google.gson.annotations.Expose;

public class AirplaneSeedDto {
    @Expose
    private String transponderNumber;
    @Expose
    private String airplaneModel;
    @Expose
    private int airplaneCapacity;

    @Expose
    private String airplaneManufacturerName;
    @Expose
    private String airplaneOperatorName;

    public AirplaneSeedDto() {
    }

    public String getTransponderNumber() {
        return this.transponderNumber;
    }

    public void setTransponderNumber(String transponderNumber) {
        this.transponderNumber = transponderNumber;
    }

    public String getAirplaneModel() {
        return this.airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public int getAirplaneCapacity() {
        return this.airplaneCapacity;
    }

    public void setAirplaneCapacity(int airplaneCapacity) {
        this.airplaneCapacity = airplaneCapacity;
    }

    public String getAirplaneManufacturerName() {
        return this.airplaneManufacturerName;
    }

    public void setAirplaneManufacturerName(String airplaneManufacturerName) {
        this.airplaneManufacturerName = airplaneManufacturerName;
    }

    public String getAirplaneOperatorName() {
        return this.airplaneOperatorName;
    }

    public void setAirplaneOperatorName(String airplaneOperatorName) {
        this.airplaneOperatorName = airplaneOperatorName;
    }
}
