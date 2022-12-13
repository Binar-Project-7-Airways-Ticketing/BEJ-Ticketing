package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.dto.ResponseData;
import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/{idBooking}")
    public ResponseEntity<ResponseData<Booking>> getBookingForTicket(@PathVariable("idBooking") Long idBooking){
        ResponseData<Booking> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(bookingService.getBookingForTicket(idBooking));
        return ResponseEntity.ok(responseData);
    }
}
