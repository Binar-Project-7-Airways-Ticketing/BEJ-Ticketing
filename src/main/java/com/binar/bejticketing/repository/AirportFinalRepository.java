package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.AirportFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AirportFinalRepository extends JpaRepository<AirportFinal,Long> {
    @Query("SELECT a FROM AirportFinal a WHERE a.airportName = :name")
    AirportFinal getAirportFinalByName(String name);
}
