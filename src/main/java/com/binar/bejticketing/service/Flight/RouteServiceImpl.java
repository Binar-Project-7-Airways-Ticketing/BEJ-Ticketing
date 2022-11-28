package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.Route;
import com.binar.bejticketing.repository.RouteRepository;
import com.binar.bejticketing.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;


    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route getRoutByAirportStartFinal(String airportStart, String airportFinal) {
        return routeRepository.getRouteByAirportStartFinal(airportStart,airportFinal);
    }

    @Override
    public Route getRouteByCityStart(String name) {
        return routeRepository.getRouteByCityStart(name);
    }

    @Override
    public Route getRouteByCityFinal(String name) {
        return routeRepository.getRouteByCityFinal(name);
    }

    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }
}
