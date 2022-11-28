package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.AirportStart;
import com.binar.bejticketing.repository.AirportStartRepository;
import com.binar.bejticketing.service.AirportStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportStartServiceImpl implements AirportStartService {

    @Autowired
    AirportStartRepository airportStartRepository;

    @Override
    public AirportStart createAirportStart(AirportStart airportStart) {
        return airportStartRepository.save(airportStart);
    }

    @Override
    public AirportStart getAirportByName(String name) {
        return airportStartRepository.getAirportStartByName(name);
    }

    @Override
    public List<AirportStart> getAllAirport() {
        return airportStartRepository.findAll();
    }
}
