package com.bulatsa.demo.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries")
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 35, nullable = false, unique = true)
    private String name;

    public Country() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
