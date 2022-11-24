package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.service.AgeCategoryService;
import com.binar.bejticketing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    private AgeCategoryService ageCategoryService;

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllListPassengers(){
        return new ResponseEntity<>(passengerService.getAllPassengers(), HttpStatus.OK);
    }

    @GetMapping("/age-category")
    public ResponseEntity<List<AgeCategory>> getAllListAgeCategories(){
        return new ResponseEntity<>(ageCategoryService.getAllAgeCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerCategoryById(@PathVariable("id") Long id){
        return new ResponseEntity<>(passengerService.getPassengerById(id), HttpStatus.OK);
    }

    @PostMapping("/create/passenger")
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger){
        return new ResponseEntity<>(passengerService.createPassenger(passenger), HttpStatus.CREATED);
    }

    @PostMapping("/create/age-category")
    public ResponseEntity<AgeCategory> createAgeCategory(@RequestBody AgeCategory ageCategory){
        return new ResponseEntity<>(ageCategoryService.createAgeCategory(ageCategory), HttpStatus.CREATED);
    }

}
