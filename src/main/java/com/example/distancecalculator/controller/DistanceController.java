package com.example.distancecalculator.controller;

import com.example.distancecalculator.entity.model.InputDistancePojo;
import com.example.distancecalculator.service.DistanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/calculation")
public class DistanceController {

    private final DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping
    public ResponseEntity<?> calculationDistance(@RequestBody InputDistancePojo inputDistancePojo){
        try {
            return ResponseEntity.ok().body(distanceService.calculationDistance(inputDistancePojo));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
