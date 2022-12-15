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

    @Override
    public Booking getBookingForTicket(Long idBooking) {
        return bookingRepository.getBookingForTicket(idBooking);
    }

    @Override
    public Booking updateStatePaymentBooking(Long idBooking, boolean state) {
        Optional<Booking> bookingChecking = bookingRepository.findById(idBooking);
        if (bookingChecking.isEmpty()){
            throw new DataNotFoundException(idBooking);
        }
        bookingChecking.get().setValid(state);
        bookingChecking.get().getBookingDetails().setStatePricing(state);
        bookingChecking.get().getBookingDetails().getPayment().setPaying(state);
        return bookingRepository.saveAndFlush(bookingChecking.get());
    }

    @Override
    public Booking updatePictureBooking(Long idBooking, String url) {
        Optional<Booking> bookingChecking = bookingRepository.findById(idBooking);
        if (bookingChecking.isEmpty()){
            throw new DataNotFoundException(idBooking);
        }
        bookingChecking.get().setPictureUrl(url);
        return bookingRepository.saveAndFlush(bookingChecking.get());
    }
}
