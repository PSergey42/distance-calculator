package com.example.distancecalculator.entity.model;

import com.example.distancecalculator.entity.City;

import java.util.UUID;

public class CityPojo {
    private UUID id;
    private String name;

    public CityPojo(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CityPojo fromEntity(City city){
        return new CityPojo(city.getId(), city.getName());
    }

    public static City toEntity(CityPojo cityPojo){
        City city = new City();
        city.setId(cityPojo.getId());
        city.setName(cityPojo.getName());
        return city;
    }
}
