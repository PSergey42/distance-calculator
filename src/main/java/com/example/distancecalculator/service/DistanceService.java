package com.example.distancecalculator.service;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;
import com.example.distancecalculator.entity.model.DistancePojo;
import com.example.distancecalculator.entity.model.InputDistancePojo;
import com.example.distancecalculator.exception.EmptyResponseFromDatabaseException;
import com.example.distancecalculator.exception.WrongTypeException;
import com.example.distancecalculator.repository.CityRepository;
import com.example.distancecalculator.repository.DistanceRepository;
import com.example.distancecalculator.util.Calculation;
import com.example.distancecalculator.util.CalculationType;
import org.springframework.stereotype.Service;

import java.util.*;
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
            throw new EmptyResponseFromDatabaseException("В базе данных нет информации о fromCities");
        }
        List<City> toCities = cityRepository.findAllById(inputDistancePojo.getToCities());
        if(toCities.isEmpty()){
            throw new EmptyResponseFromDatabaseException("В базе данных нет информации о toCities");
        }
        switch(inputDistancePojo.getType()){
            case CROWFLIGHT: {
                return calculationByTypeCrowflight(fromCities, toCities, distances, inputDistancePojo.getType());
            }
            case DISTANCE_MATRIX: {
                Set<Distance> distances1 = distanceRepository.findDistanceByFromCitiesAndToCities(fromCities, toCities);
                if(distances1.isEmpty()){
                    throw new EmptyResponseFromDatabaseException("В базе данных нет информации о расстоянии между городами из списков");
                }
                return distances1.stream().map((x) -> DistancePojo.fromEntity(x, inputDistancePojo.getType())).collect(Collectors.toSet());
            }
            case ALL:{
                Set<Distance> distances1 = distanceRepository.findDistanceByFromCitiesAndToCities(fromCities, toCities);
                if(distances1.isEmpty()){
                    throw new EmptyResponseFromDatabaseException("В базе данных нет информации о расстоянии между городами из списков");
                }
                distances.addAll(calculationByTypeCrowflight(fromCities, toCities, distances, CalculationType.CROWFLIGHT));
                distances.addAll(distances1.stream().map((x) -> DistancePojo.fromEntity(x, CalculationType.DISTANCE_MATRIX)).collect(Collectors.toSet()));
                return distances;
            }
            default: throw new WrongTypeException("Данный тип отсуствует");
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

}
