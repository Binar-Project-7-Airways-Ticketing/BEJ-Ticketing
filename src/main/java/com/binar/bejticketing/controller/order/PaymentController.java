package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.entity.Payment;
import com.binar.bejticketing.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(Long id){
        return new ResponseEntity<>(paymentService.getPaymentById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments(){
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        return new ResponseEntity<>(paymentService.createPayment(payment), HttpStatus.CREATED);
    }

    @PutMapping("/update/{idPayment}/passenger/{idPassenger}")
    public ResponseEntity<Payment> updateStatePaying(@PathVariable("idPayment") Long idPayment,
                                                     @PathVariable("idPassenger") Long idPassenger)
    {
        return new ResponseEntity<>(paymentService.updatePaymentPassenger(idPayment, idPassenger), HttpStatus.CREATED);
    }


}
