package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    List<Seat> getAllSeats();
    Seat getSeat(Long id);
    Seat createSeat(Seat seat);
    List<Seat> createSeats(List<Seat> seats);
//    Seat updateSeat(Long id);
    Seat getSeatByNumber(String numberSeat);
}
