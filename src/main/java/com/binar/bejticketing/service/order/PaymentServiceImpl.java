package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.entity.Payment;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.PassengerRepository;
import com.binar.bejticketing.repository.PaymentRepository;
import com.binar.bejticketing.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.saveAndFlush(payment);
    }

    @Override
    public Payment updatePaymentPassenger(Long idPayment, Long idPassenger) {
        Optional<Passenger> passengerChecking = passengerRepository.findById(idPassenger);
        Optional<Payment> paymentChecking = paymentRepository.findById(idPayment);
        if (passengerChecking.isEmpty() && paymentChecking.isEmpty()){
            throw new DataNotFoundException(idPassenger, idPayment);
        }
//        Long idPassenger1 = paymentChecking.get().getPassenger().getIdPassenger();
        paymentChecking.get().setPaying(true);
        return paymentChecking.get();
    }
}
