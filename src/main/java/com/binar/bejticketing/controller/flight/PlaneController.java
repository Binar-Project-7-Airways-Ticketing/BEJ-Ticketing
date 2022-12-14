package com.binar.bejticketing.controller.flight;

import com.binar.bejticketing.utils.PlaneClassEnum;
import com.binar.bejticketing.dto.PlaneDto;
import com.binar.bejticketing.dto.PlaneUpdateDto;
import com.binar.bejticketing.entity.Plane;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.service.PlaneDetailsService;
import com.binar.bejticketing.service.PlaneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plane")
public class PlaneController {
    @Autowired
    PlaneService planeService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    PlaneDetailsService planeDetailsService;

    @PostMapping("/create/plane")
    public ResponseEntity<Plane> createPlane(@RequestBody PlaneDto planeDto){
        Plane plane = modelMapper.map(planeDto,Plane.class) ;
        List<PlaneDetails> planeDetails = new ArrayList<>();
        planeDetails.add(planeDetailsService.findByName(String.valueOf(PlaneClassEnum.BUSINESS)));
        planeDetails.add(planeDetailsService.findByName(String.valueOf(PlaneClassEnum.ECONOMY)));
        plane.setPlaneClass(planeDetails);
        return new ResponseEntity<>(planeService.createPlane(plane), HttpStatus.CREATED);
    }

    @PutMapping("/update/plane")
    public ResponseEntity<Plane> updatePlane(@RequestBody PlaneUpdateDto planeUpdateDto){
        Plane plane = modelMapper.map(planeUpdateDto,Plane.class) ;
        List<PlaneDetails> planeDetails = new ArrayList<>();
        planeDetails.add(planeDetailsService.findByName(String.valueOf(PlaneClassEnum.BUSINESS)));
        planeDetails.add(planeDetailsService.findByName(String.valueOf(PlaneClassEnum.ECONOMY)));
        plane.setPlaneClass(planeDetails);
        return new ResponseEntity<>(planeService.updatePlane(plane), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/plane/{id}")
    public ResponseEntity<Plane> deletePlane(@PathVariable("id") Long id){
        planeService.deletePlane(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Plane>> getAllPlane(){
        return new ResponseEntity<>(planeService.getAllPlane(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Plane>> getPlaneById(@PathVariable("id")Long id){
        return new ResponseEntity<>(planeService.getPlaneById(id),HttpStatus.OK);
    }

//    @PostMapping("/create/plane-details")
//    public ResponseEntity<PlaneDetails> createPlaneDetails(@RequestBody PlaneDetails planeDetails){
//        return new ResponseEntity<>(planeDetailsService.createPlaneDetails(planeDetails), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update/plane-details")
//    public ResponseEntity<PlaneDetails> updatePlaneDetails(@RequestBody PlaneDetails planeDetails){
//        return new ResponseEntity<>(planeDetailsService.updatePlaneDetails(planeDetails), HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping("/delete/plane-details/{id}")
//    public ResponseEntity<String> deletePlaneDetails(@PathVariable("id") Long id){
//
//        return new ResponseEntity<>(planeDetailsService.deletePlaneDetails(id),HttpStatus.OK);
//    }
//
//    @GetMapping("/plane-details")
//    public ResponseEntity<List<PlaneDetails>> getAllPlaneDetails(){
//        return new ResponseEntity<>(planeDetailsService.getAllPlaneDetails(),HttpStatus.OK);
//    }
}
