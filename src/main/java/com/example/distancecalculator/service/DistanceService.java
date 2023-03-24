package com.example.distancecalculator.service;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;
import com.example.distancecalculator.entity.model.CityPojo;
import com.example.distancecalculator.entity.model.DistancePojo;
import com.example.distancecalculator.entity.model.InputDistancePojo;
import com.example.distancecalculator.repository.DistanceRepository;
import com.example.distancecalculator.util.Calculation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                Set<Distance> distances1 = distanceRepository.findDistanceByFromCitiesAndToCities(inputDistancePojo.getFromCities(), inputDistancePojo.getToCities());
                if(distances1.isEmpty()){
                    throw new RuntimeException();
                }
                return distances1.stream().map(DistancePojo::fromEntity).collect(Collectors.toSet());
            }
            case ALL:{
                return f(inputDistancePojo.getFromCities(), inputDistancePojo.getToCities());
            }
            default: throw new RuntimeException();
        }
    }



    private Set<DistancePojo> f(List<City> fromCity, List<City> toCity){
        Set<Distance> distances = distanceRepository.findDistanceByFromCitiesAndToCities(fromCity, toCity);
        Set<DistancePojo> absentDistances = new HashSet<>();
        int i = 0;
        for (City city1: fromCity) {
            for (City city2: toCity) {
                if(distances.isEmpty()){
                    absentDistances.add(new DistancePojo(
                            CityPojo.fromEntity(city1),
                            CityPojo.fromEntity(city2),
                            Calculation.calculationByHaversine(city1, city2)));
                }
                for (Distance distance: distances) {
                    i++;
                    if(!distance.getFromCity().equals(city1) && !distance.getToCity().equals(city2)) {
                        absentDistances.add(new DistancePojo(
                                CityPojo.fromEntity(city1),
                                CityPojo.fromEntity(city2),
                                Calculation.calculationByHaversine(city1, city2)));
                        i = 0;
                        break;
                    }
                    if(i == distances.size()){
                        i = 0;
                        absentDistances.add(new DistancePojo(
                                CityPojo.fromEntity(city1),
                                CityPojo.fromEntity(city2),
                                Calculation.calculationByHaversine(city1, city2)));
                    }
                }
            }
        }
        Set<DistancePojo> distances1 = distances.stream().map(DistancePojo::fromEntity).collect(Collectors.toSet());
        distances1.addAll(absentDistances);
        return distances1;
    }
}
