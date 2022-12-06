package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.dto.LuggageDto;
import com.binar.bejticketing.dto.ResponseData;
import com.binar.bejticketing.entity.Luggage;
import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.service.LuggageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/luggage")
public class LuggageController {
    @Autowired
    private LuggageService luggageService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Luggage>> getAllLuggage(){
        return new ResponseEntity<>(luggageService.getAllLuggage(), HttpStatus.OK);
    }

    @GetMapping("/state")
    public ResponseEntity<List<Luggage>> getLuggageByState(@RequestParam boolean state){
        return new ResponseEntity<>(luggageService.getLuggageByState(state), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Luggage>> createLuggage(@RequestBody LuggageDto luggageDto,
                                                               Errors errors){
        ResponseData<Luggage> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        Luggage luggage = modelMapper.map(luggageDto, Luggage.class);
        responseData.setPayload(luggageService.createLuggage(luggage));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Luggage> updateStateLuggage(@PathVariable Long id){
        return new ResponseEntity<>(luggageService.updateStateLuggage(id), HttpStatus.ACCEPTED);
    }
}
