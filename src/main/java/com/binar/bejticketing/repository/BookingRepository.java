package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Booking b SET b.isValid = false WHERE b.idBooking = :idBooking")
    Booking updateBookingValid(Long idBooking);

    @Query("WHERE b FROM Booking WHERE b.isValid = true AND b.idBooking = :idBooking")
    Booking getBookingForTicket(Long idBooking);
}
