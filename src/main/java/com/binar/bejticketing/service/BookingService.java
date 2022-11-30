package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.entity.BookingDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    List<Booking> getAllBooking();
    Booking getBookingById(Long id);
    Booking createBooking(Booking booking);
    BookingDetails createBookingDetail(Long idBooking, BookingDetails bookingDetails);
    Booking updateBookingDetails(Long idBooking, Long idBookingDetails);
}
