package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    @Query("SELECT p FROM Passenger p WHERE p.isDeleted = false")
    List<Passenger> getAllPassengers();

    @Query("SELECT p FROM Passenger p WHERE p.isDeleted = false AND p.idPassenger = :id")
    Passenger getPassengerById(Long id);
}
