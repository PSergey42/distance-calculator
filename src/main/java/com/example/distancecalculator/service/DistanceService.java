package com.example.distancecalculator.service;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.model.CityPojo;
import com.example.distancecalculator.entity.model.DistancePojo;
import com.example.distancecalculator.entity.model.InputDistancePojo;
import com.example.distancecalculator.repository.DistanceRepository;
import com.example.distancecalculator.util.Calculation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class DistanceService {

    private final DistanceRepository distanceRepository;

    public DistanceService(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

    public Collection<DistancePojo> calculationDistance(InputDistancePojo inputDistancePojo) {
        Set<DistancePojo> distances = new HashSet<>();
        switch(inputDistancePojo.getType()){
            case CROWFLIGHT: {
                for (City city1: inputDistancePojo.getFromCities()) {
                    for (City city2: inputDistancePojo.getToCities()) {
                        distances.add(new DistancePojo(
                                CityPojo.fromEntity(city1),
                                CityPojo.fromEntity(city2),
                                Calculation.calculationByHaversine(city1, city2)));
                    }
                }
                return distances;
            }
            case DISTANCE_MATRIX: {
                for (City city1: inputDistancePojo.getFromCities()) {
                    for (City city2: inputDistancePojo.getToCities()) {
                        distances.add(
                               DistancePojo.fromEntity(distanceRepository.
                                       findDistanceByFromCity_IdAndToCity_Id(city1.getId(), city2.getId()).get())
                        );
                    }
                }
                return distances;
            }
            case ALL:{

            }
        }
        return distances;
    }
}
