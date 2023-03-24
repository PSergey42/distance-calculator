package com.example.distancecalculator.entity.model;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;
import com.example.distancecalculator.util.CalculationType;

public class DistancePojo {
    private String fromCity;
    private String toCity;
    private CalculationType type;
    private double distance;

    public DistancePojo(){

    }
    public DistancePojo(String fromCity, String toCity, CalculationType type, double distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.type = type;
        this.distance = distance;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public CalculationType getType() {
        return type;
    }

    public void setType(CalculationType type) {
        this.type = type;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public static DistancePojo fromEntity(Distance distance, CalculationType type){
        return new DistancePojo(distance.getFromCity().getName(), distance.getToCity().getName(),
                type, distance.getDistance());
    }
}
