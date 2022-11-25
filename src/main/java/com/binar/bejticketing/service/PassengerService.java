package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PassengerService {
    Passenger createPassenger(Passenger passenger);
    List<Passenger> createPassengers(List<Passenger> passenger);
    Passenger getPassengerById(Long id);
    List<Passenger> getPassengersByName(String username);
    List<Passenger> getAllPassengers();
    String deleteDataPassenger(Long id);
    Passenger updatePassenger(Passenger passenger);
    Passenger updateAgeCategoryInPassenger(Passenger passenger,Long idPassenger, Long idAgeCategory);
}
