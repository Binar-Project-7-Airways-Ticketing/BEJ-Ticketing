package com.binar.bejticketing.service.order;

import com.binar.bejticketing.dto.SeatResponseDto;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import com.binar.bejticketing.repository.SeatRepository;
import com.binar.bejticketing.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private PlaneDetailsRepository planeDetailsRepository;

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> getSeatsByIdPlane(Long idPlane) {
        return seatRepository.getSeatsByIdPlane(idPlane);
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

    @Override
    public Seat updatePlaneDetail(Long idSeat, Long idPlane) {
        Optional<PlaneDetails> planeDetails = planeDetailsRepository.findById(idPlane);
        Optional<Seat> seat = seatRepository.findById(idSeat);

        if (planeDetails.isEmpty()){
            throw new EntityNotFoundException();
        }
        seat.get().setPlaneDetails(planeDetails.get());
        return seatRepository.saveAndFlush(seat.get());
    }
}
