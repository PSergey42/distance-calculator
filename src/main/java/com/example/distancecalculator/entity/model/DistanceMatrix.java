package com.example.distancecalculator.entity.model;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "distanceMatrix")
public class DistanceMatrix implements Serializable {

    List<City> cities;

    List<Distance> distances;

    public DistanceMatrix() {
    }

    public DistanceMatrix(List<City> cities, List<Distance> distances) {
        this.cities = cities;
        this.distances = distances;
    }

    public List<City> getCities() {
        return cities;
    }

    @XmlElementWrapper(name="cities", nillable = true)
    @XmlElement(name = "city")
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    @XmlElementWrapper(name="distances", nillable = true)
    @XmlElement(name = "distance")
    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    @Override
    public String toString() {
        return "DistanceMatrix{" +
                "cities=" + cities +
                ", distances=" + distances +
                '}';
    }
}
