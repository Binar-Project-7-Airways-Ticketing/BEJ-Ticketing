package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("id") Long id){
        return new ResponseEntity<>(seatService.getSeat(id),HttpStatus.OK);
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
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat){
        return new ResponseEntity<>(seatService.createSeat(seat), HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public ResponseEntity<List<Seat>> createSeats(@RequestBody List<Seat> seats){
        return new ResponseEntity<>(seatService.createSeats(seats), HttpStatus.CREATED);
    }
}
