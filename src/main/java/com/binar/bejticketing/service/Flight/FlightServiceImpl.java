package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.Flight;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.FlightRepository;
import com.binar.bejticketing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        Optional<Flight> byId = flightRepository.findById(flight.getIdFlight());

        if (byId.isPresent()){
            return flightRepository.save(flight);
        }
        throw new DataNotFoundException(flight.getIdFlight());
    }

    @Override
    public String deleteFlight(Long id) {
        Optional<Flight> byId = flightRepository.findById(id);

        if (byId.isPresent()){
            flightRepository.deleteFlightById(id);
            return "Delete Success";
        }

        throw  new DataNotFoundException(id);
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
    public Flight findFlightById(Long Id) {
        return flightRepository.findById(Id).orElse(null);
    }

    @Override
    public List<Flight> findAllFlightAvailable() {
        return flightRepository.getAllFlight();
    }
}
