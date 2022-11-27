package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

//    @Transactional
//    @Modifying
//    @Query("UPDATE Payment p SET p.isPaying = true WHERE p.passenger")
//    Payment updatePassengerPayment(Long idPassenger);

}
