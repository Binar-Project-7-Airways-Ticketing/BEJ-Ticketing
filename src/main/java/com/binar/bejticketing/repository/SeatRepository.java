package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
//    @Query("SELECT s FROM Seat s WHERE s.planeDetails = :idPlane")
//    List<Seat> getSeatsByIdPlane(Long idPlane);

    @Query("SELECT s FROM Seat s WHERE s.numberSeat = :number")
    List<Seat> getSeatsByNumber(String number);

//    @Transactional
//    @Modifying
//    @Query("UPDATE Seat s SET s.stateSeat = :state WHERE s.idSeat = :idSeat")
//    Seat updateSeatByState(Long idSeat, String state);
}
