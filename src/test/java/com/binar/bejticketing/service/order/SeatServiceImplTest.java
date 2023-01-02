package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.faker.MockData;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import com.binar.bejticketing.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.binar.bejticketing.utils.SeatUtils.AVAILABLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SeatServiceImplTest {

    @Mock
    private SeatRepository seatRepository;
    @InjectMocks
    private SeatServiceImpl seatService;
    @Mock
    private PlaneDetailsRepository planeDetailsRepository;

    private Seat seat;
    private PlaneDetails planeDetails;
    @BeforeEach
    private void setUp(){
        MockData mockData = new MockData();
        planeDetails = mockData.mockDataPlaneDetail(planeDetails);
        seat = mockData.mockDataSeat(seat);
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
        List<Seat> seats = new ArrayList<>();
        seats.add(seat);
        when(seatRepository.findAll()).thenReturn(seats);
        var seatList = seatService.getAllSeats();
        assertEquals(seatList, seats);
    }

    @Test
    void getSeatsByIdPlane() {
        List<Seat> seats = new ArrayList<>();
        when(planeDetailsRepository.findById(planeDetails.getIdPlaneClass())).thenReturn(Optional.ofNullable(planeDetails));
        when(seatRepository.findAll()).thenReturn(seats);
//        verify(planeDetailsRepository).findById(planeDetails.getIdPlaneClass());
    }

    @Test
    void getSeat() {
        when(seatRepository.findById(seat.getIdSeat())).thenReturn(Optional.ofNullable(seat));
    }

    @Test
    void createSeat() {
    }

    @Test
    void createSeats() {
        List<Seat> seats = new ArrayList<>();
        seats.add(seat);
        when(seatRepository.saveAllAndFlush(seats)).thenReturn(seats);
    }

    @Test
    void getSeatByNumber() {
    }

    @Test
    void updatePlaneDetail() {
    }

    @Test
    void updateStatePlaneDetail() {
        when(seatRepository.findById(planeDetails.getIdPlaneClass())).thenReturn(Optional.ofNullable(seat));

    }
}