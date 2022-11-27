package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment createPayment(Payment payment);
    Payment updatePaymentPassenger(Long idPayment, Long idPassenger);
}
