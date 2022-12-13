package com.binar.bejticketing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
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


    @Column(name = "is_valid")
    private boolean isValid = true;

    @Column(name = "session")
    private BigInteger session;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(targetEntity = History.class)
    @JsonIgnore
    private History history;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
