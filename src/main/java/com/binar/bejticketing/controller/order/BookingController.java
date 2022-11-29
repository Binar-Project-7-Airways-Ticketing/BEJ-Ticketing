package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.dto.ResponseData;
import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.entity.BookingDetails;
import com.binar.bejticketing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<ResponseData<List<Booking>>> getAllBooking(Errors errors){
        ResponseData<List<Booking>> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }


        responseData.setStatus(true);
        responseData.setPayload(bookingService.getAllBooking());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Booking>> getBookingById(@PathVariable("id") Long id, Errors errors){
        ResponseData<Booking> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }


        responseData.setStatus(true);
        responseData.setPayload(bookingService.getBookingById(id));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Booking>> createBooking(@RequestBody @Valid Booking booking,
                                                               Errors errors){
        ResponseData<Booking> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }


        responseData.setStatus(true);
        responseData.setPayload(bookingService.createBooking(booking));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/{id}/create/booking-details")
    public ResponseEntity<ResponseData<BookingDetails>> createBookingDetails(
            @PathVariable("id") Long id,
            @RequestBody @Valid BookingDetails bookingDetails,
            Errors errors){
        ResponseData<BookingDetails> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }


        responseData.setStatus(true);
        responseData.setPayload(bookingService.createBookingDetail(id, bookingDetails));
        return ResponseEntity.ok(responseData);
    }
}
