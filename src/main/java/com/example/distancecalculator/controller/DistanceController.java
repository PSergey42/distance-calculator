package com.example.distancecalculator.controller;

import com.example.distancecalculator.service.DistanceService;
import org.springframework.web.bind.annotation.*;


@RestController
public class DistanceController {

    private final DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }
}
