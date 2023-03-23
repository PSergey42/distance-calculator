package com.example.distancecalculator.controller;

import com.example.distancecalculator.service.CityService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
}
