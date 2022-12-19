package com.binar.bejticketing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "booking_details")
public class BookingDetails {
    @Id
    @Column(name = "id_booking_details")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBookingDetails;

    @Column(name = "price")
    private Double price;

    @Column(name = "state_pricing")
    private boolean statePricing = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_booking")
    @JsonIgnore
    private Booking booking;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_payment")
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_passenger")
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_luggage")
    private Luggage luggage;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_seat")
    private Seat seat;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_flight")
    private Flight flight;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
