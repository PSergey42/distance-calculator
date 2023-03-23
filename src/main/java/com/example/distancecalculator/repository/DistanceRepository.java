package com.example.distancecalculator.repository;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, UUID> {

    @Query(value = "SELECT * FROM distance b WHERE b.from_city IN :fromCities AND b.to_city IN :toCities", nativeQuery = true)
    Set<Distance> findDistanceByFromCitiesAndToCities(List<City> fromCities, List<City> toCities);
}
