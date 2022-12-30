package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Payment;
import com.binar.bejticketing.faker.MockData;
import com.binar.bejticketing.repository.PaymentRepository;
import com.binar.bejticketing.utils.PaymentMethod;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();
    Payment payment = new Payment();

    @BeforeEach
    void setUp(){
        MockData mockData = new MockData();
        payment = mockData.mockDataPayment(payment);
    }
    @Test
    void getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        payments.add(payment);
        when(paymentRepository.findAll()).thenReturn(payments);
        List<Payment> payments1 = paymentService.getAllPayments();
        assertEquals(payments1.size(), 1);
    }

    @Test
    void getPaymentById() {
        when(paymentRepository.findById(payment.getIdPayment())).thenReturn(Optional.of(payment));
        Payment payment1 = paymentService.getPaymentById(payment.getIdPayment());
        assertEquals(payment1.getIdPayment(), payment.getIdPayment());
    }

    @Test
    void createPayment() {
        when(paymentRepository.saveAndFlush(payment)).thenReturn(payment);
        Payment created = paymentService.createPayment(payment);
        assertEquals(created.getIdPayment(), payment.getIdPayment());
    }
}