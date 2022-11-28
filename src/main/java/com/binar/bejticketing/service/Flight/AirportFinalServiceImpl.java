package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.AirportFinal;
import com.binar.bejticketing.repository.AirportFinalRepository;

import com.binar.bejticketing.service.AirportFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportFinalServiceImpl implements AirportFinalService {

    @Autowired
    AirportFinalRepository airportFinalRepository;

    @Override
    public AirportFinal createAirportFinal(AirportFinal airportFinal) {
        return airportFinalRepository.save(airportFinal);
    }

    @Override
    public AirportFinal getAirportByName(String name) {
        return airportFinalRepository.getAirportFinalByName(name);
    }

    @Override
    public List<AirportFinal> getAllAirport() {
        return airportFinalRepository.findAll();
    }
}
