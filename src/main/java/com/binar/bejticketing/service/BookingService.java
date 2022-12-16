package com.binar.bejticketing.service;

import com.binar.bejticketing.dto.TicketDto;
import com.binar.bejticketing.entity.Booking;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface BookingService {
    List<Booking> getAllBooking();
    List<Booking> getBookingHistoryById(Long idBooking, Long idUser);
    Booking getBookingById(Long idBooking);
    Booking createBooking(Booking booking);
    Booking updateBookingDetails(Long idBooking, Booking booking);
    TicketDto getBookingForTicket(Long idBooking) throws ParseException;
    Booking updateStatePaymentBooking(Long idBooking, boolean state);
    Booking updatePictureBooking(Long idBooking, String url);
}
