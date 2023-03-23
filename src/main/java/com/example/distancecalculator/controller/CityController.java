package com.example.distancecalculator.controller;

import com.example.distancecalculator.entity.model.CityPojo;
import com.example.distancecalculator.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping()
    public List<CityPojo> findAll(){
        return cityService.findAll();
    }
}
