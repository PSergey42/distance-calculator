package com.example.distancecalculator.entity.model;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.util.CalculationType;

import java.util.Collection;
import java.util.List;

public class InputDistancePojo {
    private CalculationType type;
    private List<Long> fromCities;
    private List<Long> toCities;

    public InputDistancePojo(CalculationType type, List<Long> fromCities, List<Long> toCities) {
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

    public List<Long> getFromCities() {
        return fromCities;
    }

    public void setFromCities(List<Long> fromCities) {
        this.fromCities = fromCities;
    }

    public List<Long> getToCities() {
        return toCities;
    }

    public void setToCities(List<Long> toCities) {
        this.toCities = toCities;
    }
}
