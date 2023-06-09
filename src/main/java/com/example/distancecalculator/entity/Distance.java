package com.example.distancecalculator.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "distance")
@XmlRootElement(name = "distance")
public class Distance implements Serializable {
    @Id
    @Column(name = "distance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "from_city_id", nullable = false)
    private City fromCity;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_city_id", nullable = false)
    private City toCity;
    @Column(name = "distance")
    private double distance;

    public Distance(long id, City fromCity, City toCity, double distance) {
        this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public Distance() {

    }


    public long getId() {
        return id;
    }

    @XmlElement(name = "distance_id")
    public void setId(long id) {
        this.id = id;
    }

    public City getFromCity() {
        return fromCity;
    }

    @XmlElement(name = "fromCity")
    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    @XmlElement(name = "toCity")
    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public double getDistance() {
        return distance;
    }

    @XmlElement(name = "spacing")
    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + id +
                ", fromCity=" + fromCity +
                ", toCity=" + toCity +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance1 = (Distance) o;
        return id == distance1.id && Double.compare(distance1.distance, distance) == 0 && fromCity.equals(distance1.fromCity) && toCity.equals(distance1.toCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromCity, toCity, distance);
    }
}
