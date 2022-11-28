package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.AirportStart;
import com.binar.bejticketing.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r WHERE r.airportStart.airportName = :airportStart and r.airportFinal.airportName=:airportFinal")
    Route getRouteByAirportStartFinal(String airportStart, String airportFinal);

    @Query("SELECT r FROM Route r WHERE r.airportStart.city.cityName = :name")
    Route getRouteByCityStart(String name);

    @Query("SELECT r FROM Route r WHERE r.airportFinal.city.cityName = :name")
    Route getRouteByCityFinal(String name);
}
