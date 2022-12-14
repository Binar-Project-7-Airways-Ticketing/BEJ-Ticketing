package com.binar.bejticketing.service;

import com.binar.bejticketing.dto.TicketDto;
import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.entity.BookingDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface BookingService {
    List<Booking> getAllBooking();
    List<BookingDetails> getAllBookingDetails();
    List<Booking> getBookingHistoryById( Long idUser);
    List<BookingDetails> createBookingDetails(List<BookingDetails> bookingDetails);
    Booking getBookingById(Long idBooking);
    Booking createBooking(Booking booking);
    Booking updateBookingDetails(Long idBooking, Booking booking);
    Booking updateBookingState(Long idBooking, boolean state);
    TicketDto getBookingForTicket(Long idBooking) throws ParseException;
    Booking updateStatePaymentBooking(Long idBooking, boolean state);
    Booking updatePictureBooking(Long idBooking, String url);
}
