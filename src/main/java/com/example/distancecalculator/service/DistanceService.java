package com.example.distancecalculator.service;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;
import com.example.distancecalculator.entity.model.DistancePojo;
import com.example.distancecalculator.entity.model.InputDistancePojo;
import com.example.distancecalculator.repository.CityRepository;
import com.example.distancecalculator.repository.DistanceRepository;
import com.example.distancecalculator.util.Calculation;
import com.example.distancecalculator.util.CalculationType;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DistanceService {

    private final DistanceRepository distanceRepository;
    private final CityRepository cityRepository;

    public DistanceService(DistanceRepository distanceRepository, CityRepository cityRepository) {
        this.distanceRepository = distanceRepository;
        this.cityRepository = cityRepository;
    }

    public Collection<DistancePojo> calculationDistance(InputDistancePojo inputDistancePojo) {
        Set<DistancePojo> distances = new HashSet<>();
        List<City> fromCities = cityRepository.findAllById(inputDistancePojo.getFromCities());
        if(fromCities.isEmpty()){
            throw new RuntimeException();
        }
        List<City> toCities = cityRepository.findAllById(inputDistancePojo.getToCities());
        if(fromCities.isEmpty()){
            throw new RuntimeException();
        }
        switch(inputDistancePojo.getType()){
            case CROWFLIGHT: {
                return calculationByTypeCrowflight(fromCities, toCities, distances, inputDistancePojo.getType());
            }
            case DISTANCE_MATRIX: {
                Set<Distance> distances1 = distanceRepository.findDistanceByFromCitiesAndToCities(fromCities, toCities);
                if(distances1.isEmpty()){
                    throw new RuntimeException();
                }
                return distances1.stream().map((x) -> DistancePojo.fromEntity(x, inputDistancePojo.getType())).collect(Collectors.toSet());
            }
            case ALL:{
                Set<Distance> distances1 = distanceRepository.findDistanceByFromCitiesAndToCities(fromCities, toCities);
                if(distances1.isEmpty()){
                    throw new RuntimeException();
                }
                distances.addAll(calculationByTypeCrowflight(fromCities, toCities, distances, CalculationType.CROWFLIGHT));
                distances.addAll(distances1.stream().map((x) -> DistancePojo.fromEntity(x, CalculationType.DISTANCE_MATRIX)).collect(Collectors.toSet()));
                return distances;
            }
            default: throw new RuntimeException();
        }
    }

    private Set<DistancePojo> calculationByTypeCrowflight(List<City> fromCities, List<City> toCities, Set<DistancePojo> distances, CalculationType type){
        for (City fromCity: fromCities) {
            for (City toCity: toCities) {
                distances.add(new DistancePojo(
                        fromCity.getName(),
                        toCity.getName(),
                        type,
                        Calculation.calculationByHaversine(fromCity, toCity)));
            }
        }
        return distances;
    }



    //спросил про тип ALL уже после реализации этого метода (что не найдет в бд, рассчитает и добавит к результату)
    private Set<DistancePojo> f(List<City> fromCities, List<City> toCities){
        Set<Distance> distances = distanceRepository.findDistanceByFromCitiesAndToCities(fromCities, toCities);
        Set<DistancePojo> absentDistances = new HashSet<>();
        int i = 0;
        for (City fromCity: fromCities) {
            for (City toCity: toCities) {
                if(distances.isEmpty()){
                    absentDistances.add(new DistancePojo(
                            fromCity.getName(),
                            toCity.getName(),
                            CalculationType.CROWFLIGHT,
                            Calculation.calculationByHaversine(fromCity, toCity)));
                }
                for (Distance distance: distances) {
                    i++;
                    if(!distance.getFromCity().equals(fromCity) && !distance.getToCity().equals(toCity)) {
                        absentDistances.add(new DistancePojo(
                                fromCity.getName(),
                                toCity.getName(),
                                CalculationType.CROWFLIGHT,
                                Calculation.calculationByHaversine(fromCity, toCity)));
                        i = 0;
                        break;
                    }
                    if(i == distances.size()){
                        i = 0;
                        absentDistances.add(new DistancePojo(
                                fromCity.getName(),
                                toCity.getName(),
                                CalculationType.CROWFLIGHT,
                                Calculation.calculationByHaversine(fromCity, toCity)));
                    }
                }
            }
        }
        Set<DistancePojo> distances1 = distances.stream().map((x) -> DistancePojo.fromEntity(x, CalculationType.DISTANCE_MATRIX)).collect(Collectors.toSet());
        distances1.addAll(absentDistances);
        return distances1;
    }
}
