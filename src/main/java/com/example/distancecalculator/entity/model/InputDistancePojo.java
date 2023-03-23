package com.example.distancecalculator.entity.model;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.util.CalculationType;

import java.util.Collection;
import java.util.List;

public class InputDistancePojo {
    private CalculationType type;
    private List<City> fromCities;
    private List<City> toCities;

    public InputDistancePojo(CalculationType type, List<City> fromCities, List<City> toCities) {
        this.type = type;
        this.fromCities = fromCities;
        this.toCities = toCities;
    }

    public CalculationType getType() {
        return type;
    }

    public void setType(CalculationType type) {
        this.type = type;
    }

    public List<City> getFromCities() {
        return fromCities;
    }

    public void setFromCities(List<City> fromCities) {
        this.fromCities = fromCities;
    }

    public List<City> getToCities() {
        return toCities;
    }

    public void setToCities(List<City> toCities) {
        this.toCities = toCities;
    }
}
