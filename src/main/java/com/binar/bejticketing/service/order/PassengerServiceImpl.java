package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.repository.PassengerRepository;
import com.binar.bejticketing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Override
    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.getPassengerById(id);
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.getAllPassengers();
    }
}
