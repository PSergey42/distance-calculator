package com.example.distancecalculator.entity.model;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;

public class DistancePojo {
    private CityPojo fromCity;
    private CityPojo toCity;
    private double distance;

    public DistancePojo(CityPojo fromCity, CityPojo toCity, double distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public CityPojo getFromCity() {
        return fromCity;
    }

    public void setFromCity(CityPojo fromCity) {
        this.fromCity = fromCity;
    }

    public CityPojo getToCity() {
        return toCity;
    }

    public void setToCity(CityPojo toCity) {
        this.toCity = toCity;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public static DistancePojo fromEntity(Distance distance){
        return new DistancePojo(CityPojo.fromEntity(distance.getFromCity()),
                CityPojo.fromEntity(distance.getToCity()),
                distance.getDistance());
    }
}
