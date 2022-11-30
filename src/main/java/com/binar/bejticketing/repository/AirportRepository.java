package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    @Query("SELECT a FROM Airport a WHERE a.airportName = :name")
    Airport getAirportFromName(String name);

    @Query("SELECT c FROM Airport c WHERE c.city = :name")
    List<Airport> getAirportFromCity(String name);
}
