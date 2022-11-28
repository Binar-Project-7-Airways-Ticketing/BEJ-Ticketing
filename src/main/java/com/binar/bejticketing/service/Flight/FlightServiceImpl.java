package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.Flight;
import com.binar.bejticketing.repository.FlightRepository;
import com.binar.bejticketing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> findFlightSearchDate(String airportStart, String airportFinal, Date date) {
        return flightRepository.getFlightSearchDate(airportStart,airportFinal,date);
    }

    @Override
    public List<Flight> findFlightSearch(String airportStart, String airportFinal) {
        return null;
    }
}
