package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.AirportStart;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportStartService {

    AirportStart createAirportStart(AirportStart airportStart);
    AirportStart getAirportByName(String name);
    List<AirportStart> getAllAirport();
}
