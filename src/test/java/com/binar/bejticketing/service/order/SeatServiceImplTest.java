package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import com.binar.bejticketing.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.binar.bejticketing.utils.SeatUtils.AVAILABLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class SeatServiceImplTest {

    @Mock
    private SeatRepository seatRepository;
    private SeatServiceImpl seatService;
    @Mock
    private PlaneDetailsRepository planeDetailsRepository;

    private Seat seat;
    @BeforeEach
    private void setUp(){
        seatService = new SeatServiceImpl(seatRepository,planeDetailsRepository);
    }
    @Test
    void canGetAllSeats(){
        seatService.getAllSeats();
        verify(seatRepository).findAll();
    }
    @Test
    void canAddSeats(){
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        seat = Seat.builder().idSeat(1L).numberSeat("A01").stateSeat(AVAILABLE).createdAt(parse).updatedAt(null).build();

        seatService.createSeat(seat);
        verify(seatRepository).saveAndFlush(seat);
    }

    @Test
    void getSeatById(){
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        seat = Seat.builder().idSeat(1L).numberSeat("A01").stateSeat(AVAILABLE).createdAt(parse).updatedAt(null).build();

        seatService.createSeat(seat);
        verify(seatRepository).saveAndFlush(seat);
//        seatService.getSeat(1L);
        verify(seatRepository).findById(1L);
    }
    @Test
    void getAllSeats() {
    }

    @Test
    void getSeatsByIdPlane() {
    }

    @Test
    void getSeat() {
    }

    @Test
    void createSeat() {
    }

    @Test
    void createSeats() {
    }

    @Test
    void getSeatByNumber() {
    }

    @Test
    void updatePlaneDetail() {
    }

    @Test
    void updateStatePlaneDetail() {
    }
}