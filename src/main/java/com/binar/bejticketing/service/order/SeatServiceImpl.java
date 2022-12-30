package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import com.binar.bejticketing.repository.SeatRepository;
import com.binar.bejticketing.service.SeatService;
import com.binar.bejticketing.utils.SeatUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {
    private SeatRepository seatRepository;
//    @Autowired
    private PlaneDetailsRepository planeDetailsRepository;

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> getSeatsByIdPlane(Long idPlane) {
        Optional<PlaneDetails> planeDetails = planeDetailsRepository.findById(idPlane);

        if (planeDetails.isEmpty()){
            throw new EntityNotFoundException();
        }
        List<Seat> seat = seatRepository.findAll();
        List<Seat> arraySeat = new ArrayList<>();
        seat.stream().forEach(seat1 -> {
            Long idPlaneClass = seat1.getPlaneDetails().getIdPlaneClass();
            if (idPlaneClass.equals(idPlane)){
                arraySeat.add(seat1);
            }
        });

        return arraySeat;
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

    @Override
    public Seat updateStatePlaneDetail(Long idSeat, SeatUtils state) {
        Optional<Seat> seat = seatRepository.findById(idSeat);

        if (seat.isEmpty()){
            throw new DataNotFoundException(idSeat);
        }
        seat.get().setStateSeat(state);
        return seatRepository.saveAndFlush(seat.get());
    }
}
