package com.example.distancecalculator.service;

import com.example.distancecalculator.repository.DistanceRepository;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    private final DistanceRepository distanceRepository;

    public DistanceService(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

}
