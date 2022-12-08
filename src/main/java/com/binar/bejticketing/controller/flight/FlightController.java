package com.binar.bejticketing.controller.flight;

import com.binar.bejticketing.entity.Flight;
import com.binar.bejticketing.service.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@SecurityRequirement(name = "Authorize")
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
    public ResponseEntity<String> deleteFlight(@PathVariable("id")Long id){

        return new ResponseEntity<>(flightService.deleteFlight(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Flight>> getAllFlightAvailable(){
        return new ResponseEntity<>(flightService.findAllFlightAvailable(), HttpStatus.OK);
    }

    @GetMapping("/{departure-code}/{arrival-code}")
    public ResponseEntity<List<Flight>> getFlightSearch(@PathVariable("departure-code") String dCode ,@PathVariable("arrival-code") String aCode ){
        return new ResponseEntity<>(flightService.findFlightSearch(dCode,aCode), HttpStatus.OK);
    }

    @GetMapping("/{departure-code}/{arrival-code}/date")
    public ResponseEntity<List<Flight>> getFlightSearchDate(@PathVariable("departure-code") String dCode ,
                                                            @PathVariable("arrival-code") String aCode,
                                                            @RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date){
        return new ResponseEntity<>(flightService.findFlightSearchDate(dCode,aCode,date), HttpStatus.OK);
    }
}
