package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.service.SeatService;
import com.binar.bejticketing.service.order.SeatServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.binar.bejticketing.utils.SeatUtils.AVAILABLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@DataJpaTest
class SeatRepositoryTest {

    @Autowired
    private SeatRepository seatRepository;

    private Seat seat;

    @AfterEach
    void tearDown() {
        seatRepository.deleteAll();
    }

    @Test
    public void whenGetAllListSeats(){
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        seat = Seat.builder().idSeat(1L).numberSeat("A01").stateSeat(AVAILABLE).createdAt(parse).updatedAt(null).build();

        seatRepository.save(seat);

        List<Seat> seats= seatRepository.getSeatsByNumber("A01");
        assertThat(seats.get(0).getNumberSeat()).isEqualTo("A01");
    }


    @Test
    void getSeatsByNumber() {
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        seat = Seat.builder().idSeat(1L).numberSeat("A01").stateSeat(AVAILABLE).createdAt(parse).updatedAt(null).build();
        System.out.println(seat.getIdSeat());
        seatRepository.save(seat);
    }
}