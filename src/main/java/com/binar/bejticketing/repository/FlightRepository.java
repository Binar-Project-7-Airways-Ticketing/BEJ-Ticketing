package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query("SELECT f FROM Flight f WHERE f.departureCode = :departureCode AND f.arrivalCode =:arrivalCode AND f.isActive=true AND f.departureDate =:date")
    List<Flight> getFlightSearchDate(String departureCode , String arrivalCode , Date date);

    @Query("SELECT f FROM Flight f WHERE f.departureCode = :departureCode AND f.arrivalCode =:arrivalCode AND f.isActive=true ")
    List<Flight> getFlightSearch(String departureCode , String arrivalCode );

    @Query("SELECT f FROM Flight f WHERE f.isActive=true")
    List<Flight> getAllFlight();

    @Query("SELECT f FROM Flight f WHERE f.departureCode = :departureCode AND f.arrivalCode =:arrivalCode AND f.isActive=true AND f.departureDate =:date")
    Page<Flight> getFlightSearchDatePaging(String departureCode , String arrivalCode , Date date, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Flight f set f.isActive=false WHERE f.idFlight=:id")
    void deleteFlightById(Long id);
}
