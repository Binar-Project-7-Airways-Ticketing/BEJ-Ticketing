package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.AirportFinal;


import java.util.List;

public interface AirportFinalService {

    AirportFinal createAirportFinal(AirportFinal airportFinal);
    AirportFinal getAirportByName(String name);
    List<AirportFinal> getAllAirport();
}
