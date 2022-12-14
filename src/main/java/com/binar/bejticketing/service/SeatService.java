package com.binar.bejticketing.service;

import com.binar.bejticketing.dto.SeatResponseDto;
import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.utils.SeatUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    List<Seat> getAllSeats();
    List<Seat> getSeatsByIdPlane(Long idPlane);
    Seat getSeat(Long id);
    Seat createSeat(Seat seat);
    List<Seat> createSeats(List<Seat> seats);
//    Seat updateSeat(Long id);
    List<Seat> getSeatByNumber(String numberSeat);
    Seat updatePlaneDetail(Long idSeat, Long idPlane);
    Seat updateStatePlaneDetail(Long idSeat, SeatUtils state);
}
