package com.binar.bejticketing.service.order;

import com.binar.bejticketing.dto.TicketDto;
import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.entity.BookingDetails;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.BookingDetailsRepository;
import com.binar.bejticketing.repository.BookingRepository;
import com.binar.bejticketing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public List<Booking> getBookingHistoryById(Long idBooking, Long idUser) {

        Optional<Booking> bookingChecking = bookingRepository.findById(idBooking);
        if (bookingChecking.isEmpty()){
            throw new DataNotFoundException(idBooking);
        }
        Long idUserBooking = bookingChecking.get().getUser().getId();
        if (!Objects.equals(idUserBooking, idUser)){
            throw new DataNotFoundException(idUser);
        }
        return (List<Booking>) bookingChecking.get();
    }

    @Override
    public Booking getBookingById(Long idBooking) {
        return bookingRepository.findById(idBooking).get();
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
    public TicketDto getBookingForTicket(Long idBooking) {
        TicketDto ticketDto = new TicketDto();

        Optional<Booking> bookingChecking = bookingRepository.findById(idBooking);
        if (bookingChecking.isEmpty()){
            throw new DataNotFoundException(idBooking);
        }
        BookingDetails bookingDetails = bookingChecking.get().getBookingDetails();

        ticketDto.setFirstName(bookingDetails.getPassenger().getFirstName());
        ticketDto.setArrivalCode(bookingDetails.getFlight().getArrivalCode());
        ticketDto.setDepartureCode(bookingDetails.getFlight().getDepartureCode());
        ticketDto.setDepartureDate(bookingDetails.getFlight().getDepartureDate());
        ticketDto.setDepartureTime(bookingDetails.getFlight().getDepartureTime());
        ticketDto.setIdFlight(bookingDetails.getFlight().getIdFlight());
        ticketDto.setNumberSeat(bookingDetails.getSeat().getNumberSeat());

        return ticketDto;
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
