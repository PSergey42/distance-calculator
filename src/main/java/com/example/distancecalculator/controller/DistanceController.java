package com.example.distancecalculator.controller;

import com.example.distancecalculator.entity.Distance;
import com.example.distancecalculator.entity.model.DistancePojo;
import com.example.distancecalculator.entity.model.InputDistancePojo;
import com.example.distancecalculator.service.DistanceService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/calculation")
public class DistanceController {

    private final DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping
    public Collection<DistancePojo> calculationDistance(@RequestBody InputDistancePojo inputDistancePojo){
        return distanceService.calculationDistance(inputDistancePojo);
    }
}
