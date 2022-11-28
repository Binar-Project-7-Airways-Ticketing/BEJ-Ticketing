package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface FlightService {
    Flight createFlight(Flight flight);
    List<Flight> findFlightSearchDate(String airportStart , String airportFinal , Date date);
    List<Flight> findFlightSearch(String airportStart , String airportFinal );

}
