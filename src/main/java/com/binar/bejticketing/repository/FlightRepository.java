package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query("SELECT f FROM Flight f WHERE f.departureCode = :departureCode AND f.arrivalCode =:arrivalCode AND f.isActive=true AND f.departureDate=:date")
    List<Flight> getFlightSearchDate(String departureCode , String arrivalCode , Date date);

    @Query("SELECT f FROM Flight f WHERE f.departureCode = :departureCode AND f.arrivalCode =:arrivalCode AND f.isActive=true ")
    List<Flight> getFlightSearch(String departureCode , String arrivalCode );

    @Query("SELECT f FROM Flight f WHERE f.isActive=true")
    List<Flight> getAllFlight();
}
