package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
