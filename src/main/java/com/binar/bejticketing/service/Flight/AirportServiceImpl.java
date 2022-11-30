package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.repository.AirportRepository;
import com.binar.bejticketing.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportRepository airportRepository;

    @Override
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public void deleteAirport(String code) {
//        airportRepository.deleteByCode(code);
    }

    @Override
    public Airport findAirportByName(String name) {
        return airportRepository.getAirportFromName(name);
    }

    @Override
    public List<Airport> findAirportByCity(String city) {
        return airportRepository.getAirportFromCity(city);
    }

    @Override
    public List<Airport> findAllAirport() {
        return airportRepository.findAll();
    }
}
