package com.example.distancecalculator.repository;

import com.example.distancecalculator.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, UUID> {

}
