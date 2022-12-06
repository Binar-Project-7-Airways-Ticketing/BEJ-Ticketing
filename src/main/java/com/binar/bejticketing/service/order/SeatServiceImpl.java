package com.binar.bejticketing.service.order;

import com.binar.bejticketing.dto.SeatResponseDto;
import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.SeatRepository;
import com.binar.bejticketing.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeat(Long id) {
        return seatRepository.findById(id).get();
    }

    @Override
    public Seat createSeat(Seat seat) {
        return seatRepository.saveAndFlush(seat);
    }

    @Override
    public List<Seat> createSeats(List<Seat> seats) {
        return seatRepository.saveAllAndFlush(seats);
    }

//    @Override
//    public Seat updateSeat(Long id) {
//        var seat = seatRepository.findById(id);
//        if (seat.isEmpty()){
//            throw new DataNotFoundException(id);
//        }
//        return seatRepository.saveAndFlush(seat);
//    }

    @Override
    public List<Seat> getSeatByNumber(String numberSeat) {
        return seatRepository.getSeatsByNumber(numberSeat);
    }
}
