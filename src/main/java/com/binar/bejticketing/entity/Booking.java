package com.binar.bejticketing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id_booking")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBooking;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_booking_details")
    private BookingDetails bookingDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_payment")
    private Payment payment;

    @OneToOne(mappedBy = "booking")
    @JoinColumn(name = "id_passenger")
    private Passenger passenger;

    @OneToOne(mappedBy = "booking")
    @JoinColumn(name = "id_luggage")
    private Luggage luggage;

    @Column(name = "is_valid")
    private boolean isValid = true;

    @ManyToOne
    @JoinColumn(name = "id_luggage")
    private User user;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
