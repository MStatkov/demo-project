package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "airplane_manufacturers")
public class AirplaneManufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false, unique = true)
    private String name;

    public AirplaneManufacturer() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
