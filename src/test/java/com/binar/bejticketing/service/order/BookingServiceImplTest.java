package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.*;
import com.binar.bejticketing.faker.MockData;
import com.binar.bejticketing.repository.BookingDetailsRepository;
import com.binar.bejticketing.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {
    @Mock private BookingRepository bookingRepository;
    @Mock private BookingDetailsRepository bookingDetailsRepository;
    @InjectMocks private BookingServiceImpl bookingService;

    Booking booking = new Booking();
    List<Booking> bookings = new ArrayList<>();
    List<BookingDetails> bookingDetails = new ArrayList<>();
    BookingDetails bookingDetail = new BookingDetails();
    Luggage luggage = new Luggage();
    Seat seat = new Seat();
    Passenger passenger = new Passenger();
    Flight flight = new Flight();
    @BeforeEach
    void setUp() throws ParseException {
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        MockData mockData = new MockData();
        Payment payment = new Payment();
        payment = mockData.mockDataPayment(payment);
        flight = mockData.mockDataFlight(flight);
        passenger = mockData.mockDataPassenger(passenger);
        luggage = mockData.mockDataLuggage(luggage);
        booking.setIdBooking(1L);
        booking.setPaying(false);
        booking.setPictureUrl("img");
        booking.setValid(true);
        booking.setCreatedAt(parse);
        booking.setPayment(payment);
        bookingDetail.setIdBookingDetails(1L);
        bookingDetail.setStatePricing(false);
        bookingDetail.setCreatedAt(parse);

        bookingDetail.setFlight(flight);
        bookingDetail.setSeat(seat);
        bookingDetail.setLuggage(luggage);
        bookingDetail.setPassenger(passenger);
        bookingDetails.add(bookingDetail);
        booking.setBookingDetails(bookingDetails);

        bookings.add(booking);
    }

    @Test
    void getAllBooking() {
        when(bookingRepository.findAll()).thenReturn(bookings);
        var bookingAll = bookingService.getAllBooking();
        assertEquals(bookingAll, bookings);
    }

    @Test
    void getAllBookingReturnFalse() {
        when(bookingRepository.findAll()).thenReturn(bookings);
        var bookingAll = bookingService.getAllBooking();
        assertEquals(bookingAll, bookings);
    }

    @Test
    void getAllBookingDetails() {
        when(bookingDetailsRepository.findAll()).thenReturn(bookingDetails);
        var bookingById = bookingService.getAllBookingDetails();
        assertEquals(bookingById, bookingDetails);
    }

    @Test
    void getBookingHistoryById() {
        when(bookingRepository.findAll()).thenReturn(bookings);
    }

    @Test
    void createBookingDetails() {
        when(bookingDetailsRepository.saveAll(bookingDetails)).thenReturn(bookingDetails);
        var saveBookings = bookingService.createBookingDetails(bookingDetails);
        assertEquals(saveBookings, bookingDetails);

    }

    @Test
    void getBookingById() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.ofNullable(booking));
        var bookingById = bookingService.getBookingById(booking.getIdBooking());
        assertEquals(bookingById, booking);
    }

    @Test
    void createBooking() {
        when(bookingRepository.saveAndFlush(booking)).thenReturn(booking);
        var saveBooking =bookingService.createBooking(booking);
        assertEquals(saveBooking, booking);
    }

    @Test
    void updateBookingDetails() {
        when(bookingRepository.findById(booking.getIdBooking())).thenReturn(Optional.ofNullable(booking));
        when(bookingRepository.saveAndFlush(booking)).thenReturn(booking);
        var saveBooking = bookingService.updateBookingDetails(booking.getIdBooking(), booking);
        assertEquals(saveBooking, booking);
    }

    @Test
    void updateStatePaymentBooking() {
        when(bookingRepository.findById(booking.getIdBooking())).thenReturn(Optional.ofNullable(booking));
        when(bookingRepository.saveAndFlush(booking)).thenReturn(booking);
        var bookingUpdate = bookingService.updateStatePaymentBooking(booking.getIdBooking(), true);
        assertEquals(bookingUpdate, booking);
    }

    @Test
    void updatePictureBooking() {
        when(bookingRepository.findById(booking.getIdBooking())).thenReturn(Optional.ofNullable(booking));
        when(bookingRepository.saveAndFlush(booking)).thenReturn(booking);
        var bookingUpdate = bookingService.updatePictureBooking(booking.getIdBooking(), "data:image/jpeg;base64,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");
        assertEquals(bookingUpdate, booking);
    }
}