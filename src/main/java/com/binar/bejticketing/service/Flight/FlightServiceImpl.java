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
    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
    flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> findFlightSearchDate(String departureCode , String arrivalCode , Date date) {
        return flightRepository.getFlightSearchDate(departureCode,arrivalCode,date);
    }

    @Override
    public List<Flight> findFlightSearch(String departureCode , String arrivalCode) {
        return flightRepository.getFlightSearch(departureCode,arrivalCode);
    }

    @Override
    public List<Flight> findAllFlightAvailable() {
        return flightRepository.getAllFlight();
    }
}
