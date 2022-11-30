package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService {
    Airport createAirport(Airport airport);
    Airport updateAirport(Airport airport);
    void deleteAirport(String code);
    Airport findAirportByName(String name);
    List<Airport> findAirportByCity(String city);
    List<Airport> findAllAirport();


}
