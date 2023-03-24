package com.example.distancecalculator.util;

import com.example.distancecalculator.entity.City;

public class Calculation {
    public static final double EARTH_RADIUS = 6372.795;

    public static double calculationByHaversine(City city1, City city2){
        String result = String.format("%.1f",(2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin((parse(city2.getLatitude()) - parse(city1.getLatitude()))/2) ,2) +
                        Math.cos(parse(city1.getLatitude())) * Math.cos(parse(city2.getLatitude())) *
                                Math.pow(Math.sin((parse(city2.getLongitude()) - parse(city1.getLongitude()))/2) ,2)))) * EARTH_RADIUS).replace(",", ".");
        return Double.parseDouble(result);
    }

    private static double parse(double coordinate){
        return (Math.PI * coordinate)/180;
    }
}
