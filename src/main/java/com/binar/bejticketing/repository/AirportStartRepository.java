package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.AirportStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AirportStartRepository extends JpaRepository<AirportStart,Long> {
    @Query("SELECT a FROM AirportFinal a WHERE a.airportName = :name")
    AirportStart getAirportStartByName(String name);
}
