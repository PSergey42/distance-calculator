package com.example.distancecalculator.service;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;
import com.example.distancecalculator.entity.model.DistanceMatrix;
import com.example.distancecalculator.util.JaxbWorker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private final JdbcTemplate jdbcTemplate;

    public FileService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void saveXmlInDataBase(MultipartFile file){
      DistanceMatrix distanceMatrix = JaxbWorker.fromXmlToObject(file);
      saveAll(distanceMatrix.getCities(), distanceMatrix.getDistances());
    }

    //оптимизация большого объема данных
    public void saveAll(List<City> cities, List<Distance> distances) {
        List<Object[]> values = new ArrayList<>();
        for (City city : cities) {
            Object[] item = new Object[] {city.getId(), city.getName(), city.getLatitude(), city.getLongitude()};
            values.add(item);
        }
        jdbcTemplate.batchUpdate("INSERT INTO city (city_id, name, latitude, longitude) VALUES (?, ?, ?, ?)",
                values);
        values.clear();
        for (Distance distance : distances) {
            Object[] item = new Object[] {distance.getId(), distance.getFromCity().getId(), distance.getToCity().getId(), distance.getDistance()};
            values.add(item);
        }
        jdbcTemplate.batchUpdate("INSERT INTO distance (distance_id, from_city_id, to_city_id, distance) VALUES (?, ?, ?, ?)",
                values);
    }
}
