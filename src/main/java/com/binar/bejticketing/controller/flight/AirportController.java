package com.binar.bejticketing.controller.flight;

import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

    @Autowired
    AirportService airportService;

    @PostMapping("/create")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport){
        return new ResponseEntity<>(airportService.createAirport(airport), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Airport> updateAirport(@RequestBody Airport airport){
        return new ResponseEntity<>(airportService.updateAirport(airport), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirport(){
        return new ResponseEntity<>(airportService.findAllAirport(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Airport> getAirportByName(@PathVariable("name") String name){
        return new ResponseEntity<>(airportService.findAirportByName(name), HttpStatus.OK);
    }

    @GetMapping("/{city}")
    public ResponseEntity<List<Airport>> getAirportByCity(@PathVariable("city") String city){
        return new ResponseEntity<>(airportService.findAirportByCity(city), HttpStatus.OK);
    }
}
