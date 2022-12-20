package com.binar.bejticketing.controller.flight;

import com.binar.bejticketing.dto.FlightDto;
import com.binar.bejticketing.dto.FlightUpdateDTO;
import com.binar.bejticketing.dto.PriceDto;
import com.binar.bejticketing.entity.*;
import com.binar.bejticketing.service.FlightService;
import com.binar.bejticketing.service.PlaneDetailsService;
import com.binar.bejticketing.service.PlaneService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    FlightService flightService;

    @Autowired
    PlaneDetailsService planeDetailsService;
    @Autowired
    PlaneService planeService;
    @PostMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDto flightDto){
        Flight flight = modelMapper.map(flightDto,Flight.class) ;
        Optional<Plane> plane = planeService.getPlaneById(flightDto.getPlane());
        flight.setPlane(plane.orElse(null));


        return new ResponseEntity<>(flightService.createFlight(flight), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Flight> updateFlight(@RequestBody FlightUpdateDTO flightUpdateDTO){
        Flight flight = modelMapper.map(flightUpdateDTO ,Flight.class) ;
        Optional<Plane> plane = planeService.getPlaneById(flightUpdateDTO.getPlane());
        flight.setPlane(plane.orElse(flightService.findFlightById(flightUpdateDTO.getIdFlight()).getPlane()));
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
                                                            @RequestParam("date")@DateTimeFormat(pattern="MM/dd/yyyy") Date date){

        return new ResponseEntity<>(flightService.findFlightSearchDate(dCode,aCode,date), HttpStatus.OK);
    }

    @GetMapping("/paging/{size}/{page}")
    public ResponseEntity<Iterable<Flight>> getFlightSearchDatePaging(@RequestParam("departure-code") String dCode ,
                                                                  @RequestParam("arrival-code") String aCode,
                                                                  @PathVariable("size") int size ,
                                                                  @PathVariable("page") int page,
                                                            @RequestParam("date")@DateTimeFormat(pattern="MM/dd/yyyy") Date date){

        Pageable pageable = PageRequest.of(page-1,size);
        return new ResponseEntity<>(flightService.findFlightSearchDatePaging(dCode,aCode,date,pageable), HttpStatus.OK);
    }


    @GetMapping("/price/{id}")
    public ResponseEntity<PriceDto> getPrice(@PathVariable("id") Long id){
        PriceDto priceDto = new PriceDto();
        Flight flight = flightService.findFlightById(id);
        PlaneDetails planeDetails = planeDetailsService.findByName("BUSINESS");
        priceDto.setEconomy(flight.getPrice());
        priceDto.setBusiness(flight.getPrice()+planeDetails.getPrice());

        return new ResponseEntity<>(priceDto,HttpStatus.OK);
    }
}
