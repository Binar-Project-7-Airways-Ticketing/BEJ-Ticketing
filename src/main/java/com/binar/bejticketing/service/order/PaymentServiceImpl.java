package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Payment;
import com.binar.bejticketing.repository.PassengerRepository;
import com.binar.bejticketing.repository.PaymentRepository;
import com.binar.bejticketing.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
