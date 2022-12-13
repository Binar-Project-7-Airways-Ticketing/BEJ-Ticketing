package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    List<Booking> getAllBooking();
    Booking getBookingById(Long id);
    Booking createBooking(Booking booking);
    Booking updateBookingDetails(Long idBooking, Booking booking);
    Booking getBookingForTicket(Long idBooking);
}
