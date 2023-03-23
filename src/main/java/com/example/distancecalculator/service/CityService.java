package com.example.distancecalculator.service;

import com.example.distancecalculator.entity.model.CityPojo;
import com.example.distancecalculator.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityPojo> findAll(){
        return cityRepository.findAll().stream().map(CityPojo::fromEntity).collect(Collectors.toList());
    }

}
