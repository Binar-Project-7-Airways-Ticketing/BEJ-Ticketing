package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    @Query("SELECT p FROM Passenger p WHERE p.isDeleted = false")
    List<Passenger> getAllPassengers();

    @Query("SELECT p FROM Passenger p WHERE p.isDeleted = false AND p.idPassenger = :id")
    Passenger getPassengerById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Passenger p SET p.isDeleted = true WHERE p.idPassenger = :id")
    int deletePassenger(Long id);

    @Query("SELECT p FROM Passenger p WHERE p.firstName = :firstname")
    List<Passenger> getPassengersByName(String firstname);


}
