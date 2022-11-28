package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {
    Route createRoute(Route route);
    Route getRoutByAirportStartFinal(String airportStart,String airportFinal);

    Route getRouteByCityStart(String name);

    Route getRouteByCityFinal(String name);

    List<Route> getAllRoute();
}
