package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query("SELECT f FROM Flight f WHERE f.route.airportStart.airportName = :airportStart and  f.route.airportFinal.airportName = :airportFinal and f.isActive=TRUE and f.departureDate=:date")
    List<Flight> getFlightSearchDate(String airportStart , String airportFinal , Date date);

    @Query("SELECT f FROM Flight f WHERE f.route.airportStart.airportName = :airportStart and  f.route.airportFinal.airportName = :airportFinal and f.isActive=TRUE")
    List<Flight> getFlightSearch(String airportStart , String airportFinal);
}
