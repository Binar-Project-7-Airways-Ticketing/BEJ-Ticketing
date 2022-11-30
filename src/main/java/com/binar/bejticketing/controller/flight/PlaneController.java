package com.binar.bejticketing.controller.flight;

import com.binar.bejticketing.entity.Plane;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.service.PlaneDetailsService;
import com.binar.bejticketing.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plane")
public class PlaneController {
    @Autowired
    PlaneService planeService;

    @Autowired
    PlaneDetailsService planeDetailsService;

    @PostMapping("/create/plane")
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane){
        return new ResponseEntity<>(planeService.createPlane(plane), HttpStatus.CREATED);
    }

    @PutMapping("/update/plane")
    public ResponseEntity<Plane> updatePlane(@RequestBody Plane plane){
        return new ResponseEntity<>(planeService.updatePlane(plane), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/plane/{id}")
    public ResponseEntity<Plane> deletePlane(@PathVariable("id") Long id){
        planeService.deletePlane(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/plane")
    public ResponseEntity<List<Plane>> getAllPlane(){
        return new ResponseEntity<>(planeService.getAllPlane(),HttpStatus.OK);
    }
    @GetMapping("/plane/{id}")
    public ResponseEntity<Optional<Plane>> getPlaneById(@PathVariable("id")Long id){
        return new ResponseEntity<>(planeService.getPlaneById(id),HttpStatus.OK);
    }

    @PostMapping("/create/plane-details")
    public ResponseEntity<PlaneDetails> createPlaneDetails(@RequestBody PlaneDetails planeDetails){
        return new ResponseEntity<>(planeDetailsService.createPlaneDetails(planeDetails), HttpStatus.CREATED);
    }

    @PutMapping("/update/plane-details")
    public ResponseEntity<PlaneDetails> updatePlaneDetails(@RequestBody PlaneDetails planeDetails){
        return new ResponseEntity<>(planeDetailsService.updatePlaneDetails(planeDetails), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/plane-details/{id}")
    public ResponseEntity<PlaneDetails> deletePlaneDetails(@PathVariable("id") Long id){
        planeDetailsService.deletePlaneDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/plane-details")
    public ResponseEntity<List<PlaneDetails>> getAllPlaneDetails(){
        return new ResponseEntity<>(planeDetailsService.getAllPlaneDetails(),HttpStatus.OK);
    }
}
