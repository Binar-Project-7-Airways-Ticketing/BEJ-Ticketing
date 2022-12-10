package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.AirportRepository;
import com.binar.bejticketing.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Airport> byId = airportRepository.getAirportFromCode(airport.getAirportCode());

        if (byId.isPresent()){
            return airportRepository.save(airport);
        }
        throw new DataNotFoundException(airport.getAirportCode());

    }

    @Override
    public String deleteAirport(String code) {
        Optional<Airport> byId = airportRepository.getAirportFromCode(code);

        if (byId.isPresent()){

            airportRepository.deleteByAirportCode(code);
            return "Delete Successful";
        }
        throw new DataNotFoundException(code);

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

    @Override
    public Optional<Airport> findAirportByCode(String code) {
        return airportRepository.getAirportFromCode(code);
    }

    @Override
    public void setImage(String url, String city) {

        airportRepository.uploadImage(url,city);

    }

}
