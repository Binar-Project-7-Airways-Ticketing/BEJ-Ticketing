package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AirportService {
    Airport createAirport(Airport airport);
    Airport updateAirport(Airport airport);
    String deleteAirport(String code);
    Airport findAirportByName(String name);
    List<Airport> findAirportByCity(String city);
    List<Airport> findAllAirport();

    Optional<Airport> findAirportByCode(String code);
    void setImage(String url , String city);


}
