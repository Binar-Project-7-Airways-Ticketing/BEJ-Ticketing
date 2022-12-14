package com.binar.bejticketing.controller.order;
import com.binar.bejticketing.dto.ResponseData;

import com.binar.bejticketing.dto.SeatResponseDto;
import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.service.SeatService;
import com.binar.bejticketing.utils.SeatUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("id") Long id){
        return new ResponseEntity<>(seatService.getSeat(id),HttpStatus.OK);
    }

    @GetMapping("/plane-detail/{idPlane}")
    public ResponseEntity<List<Seat>> getSeatByIdPlane(@PathVariable("idPlane") Long idPlane){
        return new ResponseEntity<>(seatService.getSeatsByIdPlane(idPlane),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats(){
        return new ResponseEntity<>(seatService.getAllSeats(), HttpStatus.OK);
    }

    @GetMapping("/number")
    public ResponseEntity<List<Seat>> getAllSeatsByNumber(@RequestParam String number){
        return new ResponseEntity<>(seatService.getSeatByNumber(number), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Seat>> createSeat(@Valid @RequestBody SeatResponseDto seatDto,
                                                         Errors errors){
        ResponseData<Seat> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        Seat seat = modelMapper.map(seatDto, Seat.class);
        responseData.setPayload(seatService.createSeat(seat));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/creates")
    public ResponseEntity<ResponseData<List<Seat>>> createSeats(@Valid @RequestBody List<SeatResponseDto> seats,
                                                  Errors errors){
        ResponseData<List<Seat>> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        Seat seat;
        List<Seat> seatList = new ArrayList<>();
        for(SeatResponseDto s: seats){
            seat = modelMapper.map(s, Seat.class);
            seatList.add(seat);
        }
        responseData.setPayload(seatService.createSeats(seatList));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id-seat}/update/{id-plane}")
    public ResponseEntity<ResponseData<Seat>> updatePlaneDetails(@PathVariable("id-seat") Long idSeat,
                                                                 @PathVariable("id-plane") Long idPlane){
        ResponseData<Seat> responseData = new ResponseData<>();
        responseData.setStatus(true);

        responseData.setPayload(seatService.updatePlaneDetail(idSeat,idPlane));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update/{id-seat}/{state}")
    public ResponseEntity<ResponseData<Seat>> updatePlaneDetails(@PathVariable("id-seat") Long idSeat,
                                                                 @PathVariable("state") SeatUtils state){
        ResponseData<Seat> responseData = new ResponseData<>();
        responseData.setStatus(true);

        responseData.setPayload(seatService.updateStatePlaneDetail(idSeat,state));
        return ResponseEntity.ok(responseData);
    }
}
