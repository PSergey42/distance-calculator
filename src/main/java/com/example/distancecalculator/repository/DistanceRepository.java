package com.example.distancecalculator.repository;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {

    @Query(value = "SELECT * FROM distance WHERE from_city_id IN :fromCities AND to_city_id IN :toCities", nativeQuery = true)
    Set<Distance> findDistanceByFromCitiesAndToCities(List<City> fromCities, List<City> toCities);

}
