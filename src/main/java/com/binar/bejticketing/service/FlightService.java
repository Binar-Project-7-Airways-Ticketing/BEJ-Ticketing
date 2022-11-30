package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface FlightService {
    Flight createFlight(Flight flight);
    Flight updateFlight(Flight flight);
    void deleteFlight(Long id);
    List<Flight> findFlightSearchDate(String departureCode , String arrivalCode , Date date);
    List<Flight> findFlightSearch(String departureCode , String arrivalCode );

    List<Flight> findAllFlightAvailable();
}
