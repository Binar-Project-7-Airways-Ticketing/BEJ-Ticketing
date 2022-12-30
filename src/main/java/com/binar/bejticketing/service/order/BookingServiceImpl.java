package com.binar.bejticketing.service.order;

import com.binar.bejticketing.dto.TicketDto;
import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.entity.BookingDetails;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.BookingDetailsRepository;
import com.binar.bejticketing.repository.BookingRepository;
import com.binar.bejticketing.service.BookingService;
import com.binar.bejticketing.utils.ConvertDateToTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public List<BookingDetails> getAllBookingDetails() {
        return bookingDetailsRepository.findAll();
    }

    @Override
    public List<Booking> getBookingHistoryById(Long idUser) {
        List<Booking> booking = bookingRepository.findAll();
        List<Booking> getBooking = new ArrayList<>();
        booking.forEach(booking1 -> {
            if (booking1.getUser().getId().equals(idUser)){
                getBooking.add(booking1);
            }
        });
        return booking;
    }

    @Override
    public List<BookingDetails> createBookingDetails(List<BookingDetails> bookingDetails) {
        return bookingDetailsRepository.saveAll(bookingDetails);
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
    public Booking updateBookingState(Long idBooking, boolean state) {
        return null;
    }

    @Override
    public TicketDto getBookingForTicket(Long idBooking) throws ParseException {
        TicketDto ticketDto = new TicketDto();

        Optional<Booking> bookingChecking = bookingRepository.findById(idBooking);
        if (bookingChecking.isEmpty()){
            throw new DataNotFoundException(idBooking);
        }
        for (BookingDetails bookingDetails: bookingChecking.get().getBookingDetails()){
            ticketDto.setArrivalCode(bookingDetails.getFlight().getArrivalCode());
            ticketDto.setDepartureCode(bookingDetails.getFlight().getDepartureCode());

            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            DateFormat dateTime = new SimpleDateFormat("hh:mm:ss");
            ConvertDateToTime convertDateToTime = new ConvertDateToTime();


            Date departureDateConvert = convertDateToTime.convertDate(bookingDetails.getFlight().getDepartureDate());
            Date departureTimeConvert = convertDateToTime.convertTime(bookingDetails.getFlight().getDepartureTime());
            ticketDto.setDepartureDate(dateFormat.format(departureDateConvert));
            ticketDto.setDepartureTime(dateTime.format(departureTimeConvert));

            ticketDto.setIdFlight(bookingDetails.getFlight().getIdFlight());
            ticketDto.setNumberSeat(bookingDetails.getSeat().getNumberSeat());
        }



        return ticketDto;
    }

    @Override
    public Booking updateStatePaymentBooking(Long idBooking, boolean state) {
        Optional<Booking> bookingChecking = bookingRepository.findById(idBooking);
        if (bookingChecking.isEmpty()){
            throw new DataNotFoundException(idBooking);
        }
        bookingChecking.get().setPaying(state);
        for (BookingDetails bookingDetails: bookingChecking.get().getBookingDetails()){
            bookingDetails.setStatePricing(state);
        }
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
