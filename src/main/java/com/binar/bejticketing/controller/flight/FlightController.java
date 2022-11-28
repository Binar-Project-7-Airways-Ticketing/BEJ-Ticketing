package com.binar.bejticketing.controller.flight;

import com.binar.bejticketing.entity.Flight;
import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        return new ResponseEntity<>(flightService.createFlight(flight), HttpStatus.CREATED);
    }
}
