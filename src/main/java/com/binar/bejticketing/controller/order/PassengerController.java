package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.exception.DataNotFoundException;
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

//    Get Data
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllListPassengers(){
        return new ResponseEntity<>(passengerService.getAllPassengers(), HttpStatus.OK);
    }

    @GetMapping("/age-category")
    public ResponseEntity<List<AgeCategory>> getAllListAgeCategories(){
        return new ResponseEntity<>(ageCategoryService.getAllAgeCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable("id") Long id){
        return new ResponseEntity<>(passengerService.getPassengerById(id), HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<List<Passenger>> getPassengerByName(@RequestParam String username){
        return new ResponseEntity<>(passengerService.getPassengersByName(username), HttpStatus.OK);
    }

//    Create
    @PostMapping("/create/passenger")
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger){
        return new ResponseEntity<>(passengerService.createPassenger(passenger), HttpStatus.CREATED);
    }

    @PostMapping("/create/passengers")
    public ResponseEntity<List<Passenger>> createPassengers(@RequestBody List<Passenger> passenger){
        return new ResponseEntity<>(passengerService.createPassengers(passenger), HttpStatus.CREATED);
    }

    @PostMapping("/create/age-category")
    public ResponseEntity<AgeCategory> createAgeCategory(@RequestBody AgeCategory ageCategory){
        return new ResponseEntity<>(ageCategoryService.createAgeCategory(ageCategory), HttpStatus.CREATED);
    }

//    Update

    @PutMapping("/edit/age-category")
    public ResponseEntity<AgeCategory> updateAgeCategory(@RequestBody AgeCategory ageCategory){
        return new ResponseEntity<>(ageCategoryService.updateAgeCategory(ageCategory), HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit/passenger")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger){
        return new ResponseEntity<>(passengerService.updatePassenger(passenger), HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit/passenger/{idPassenger}/age-category/{idAgeCategory}")
    public ResponseEntity<Passenger> updateAgeCategoryInPassenger(@PathVariable("idPassenger") Long idPassenger,
                                                                  @PathVariable("idAgeCategory") Long idAgeCategory){
        return new ResponseEntity<>(passengerService.updateAgeCategoryInPassenger(
                idPassenger,
                idAgeCategory), HttpStatus.ACCEPTED);
    }

//    Delete

    @DeleteMapping("/delete/age-category/{id}")
    public ResponseEntity<String> deleteAgeCategory(@PathVariable("id") Long id){
        ageCategoryService.deleteAgeCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/passenger/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable("id") Long id){
        return new ResponseEntity<>(passengerService.deleteDataPassenger(id), HttpStatus.NO_CONTENT);
    }

}
