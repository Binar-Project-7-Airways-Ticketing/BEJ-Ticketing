package com.binar.bejticketing.controller.flight;

import com.binar.bejticketing.entity.Flight;
import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        return new ResponseEntity<>(flightService.createFlight(flight), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight){
        return new ResponseEntity<>(flightService.updateFlight(flight), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable("id")Long id){
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Flight>> getAllFlightAvailable(){
        return new ResponseEntity<>(flightService.findAllFlightAvailable(), HttpStatus.OK);
    }

    @GetMapping("/{departure-code}/{arrival-code}")
    public ResponseEntity<List<Flight>> getFlightSearch(@PathVariable("departure-code") String dCode ,@PathVariable("arrival-code") String aCode ){
        return new ResponseEntity<>(flightService.findFlightSearch(dCode,aCode), HttpStatus.OK);
    }

    @GetMapping("/{departure-code}/{arrival-code}/{date}")
    public ResponseEntity<List<Flight>> getFlightSearchDate(@PathVariable("departure-code") String dCode ,@PathVariable("arrival-code") String aCode,@PathVariable("date") Date date  ){
        return new ResponseEntity<>(flightService.findFlightSearchDate(dCode,aCode,date), HttpStatus.OK);
    }
}
