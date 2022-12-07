package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.BookingDetailsRepository;
import com.binar.bejticketing.repository.BookingRepository;
import com.binar.bejticketing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.saveAndFlush(booking);
    }

    @Override
    public Booking updateBookingDetails(Long idBooking, Booking booking) {
        Optional<Booking> bookingChecking = bookingRepository.findById(idBooking);
        if (bookingChecking.isEmpty()){
            throw new DataNotFoundException(idBooking);
        }
        return bookingRepository.saveAndFlush(booking);
    }
}
