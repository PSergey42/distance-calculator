package com.example.distancecalculator.service;

import com.example.distancecalculator.entity.model.DistanceMatrix;
import com.example.distancecalculator.repository.CityRepository;
import com.example.distancecalculator.repository.DistanceRepository;
import com.example.distancecalculator.util.JaxbWorker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final DistanceRepository distanceRepository;
    private final CityRepository cityRepository;

    public FileService(DistanceRepository distanceRepository, CityRepository cityRepository) {
        this.distanceRepository = distanceRepository;
        this.cityRepository = cityRepository;
    }

    public void saveXmlInDataBase(MultipartFile file){
      DistanceMatrix distanceMatrix = JaxbWorker.fromXmlToObject(file);
      System.out.println(distanceMatrix);
      /*cityRepository.saveAll(distanceMatrix.getCities()); // не оптимизированно
      distanceRepository.saveAll(distanceMatrix.getDistances()); // не оптимизированно*/
    }
}
