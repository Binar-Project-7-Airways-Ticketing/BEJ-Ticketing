package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.entity.Luggage;
import com.binar.bejticketing.service.LuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/luggage")
public class LuggageController {
    @Autowired
    private LuggageService luggageService;

    @GetMapping
    public ResponseEntity<List<Luggage>> getAllLuggage(){
        return new ResponseEntity<>(luggageService.getAllLuggage(), HttpStatus.OK);
    }

    @GetMapping("/state")
    public ResponseEntity<List<Luggage>> getLuggageByState(@RequestParam boolean state){
        return new ResponseEntity<>(luggageService.getLuggageByState(state), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Luggage> createLuggage(@RequestBody Luggage luggage){
        return new ResponseEntity<>(luggageService.createLuggage(luggage), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Luggage> updateStateLuggage(@PathVariable Long id){
        return new ResponseEntity<>(luggageService.updateStateLuggage(id), HttpStatus.ACCEPTED);
    }
}
