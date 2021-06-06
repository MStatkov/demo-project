package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "airplanes")
public class Airplane implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transponder_number", nullable = false, length = 30, unique = true)
    private String transponderNumber;

    @ManyToOne(optional = false, targetEntity = AirplaneManufacturer.class)
    @JoinColumn(name = "airplane_manufacturer_name", nullable = false, referencedColumnName = "name")
    private AirplaneManufacturer airplaneManufacturer;

    @Column(name = "airplane_model", nullable = false, length = 20)
    private String airplaneModel;

    @Column(name = "airplane_capacity", nullable = false)
    private int airplaneCapacity;

    @ManyToOne(optional = false, targetEntity = AirOperator.class)
    @JoinColumn(name = "air_operator_name", nullable = false, referencedColumnName = "name")
    private AirOperator airOperator;

    public Airplane() {
    }

    public String getTransponderNumber() {
        return this.transponderNumber;
    }

    public void setTransponderNumber(String transponderNumber) {
        this.transponderNumber = transponderNumber;
    }

    public AirplaneManufacturer getAirplaneManufacturer() {
        return this.airplaneManufacturer;
    }

    public void setAirplaneManufacturer(AirplaneManufacturer airplaneManufacturer) {
        this.airplaneManufacturer = airplaneManufacturer;
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

    public AirOperator getAirOperator() {
        return this.airOperator;
    }

    public void setAirOperator(AirOperator airOperator) {
        this.airOperator = airOperator;
    }
}
