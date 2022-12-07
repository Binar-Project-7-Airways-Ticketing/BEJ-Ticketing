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
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flight")
    private Long idFlight;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "departure_code")
    private String departureCode;

    @Column(name = "arrival_code")
    private String arrivalCode;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "departure_date")
    private Date departureDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "arrival_date")
    private Date arrivalDate;

    @JsonFormat(pattern = "hh:mm")
    @Column(name = "departure_time")
    private Date departureTime;

    @JsonFormat(pattern = "hh:mm")
    @Column(name = "arrival_time")
    private Date arrivalTime;

    @JoinColumn(name = "id_plane", referencedColumnName = "id_plane")
    @ManyToOne
    private Plane plane;

    @Column(name = "price")
    private Long price;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToOne(mappedBy = "flight")
    @JoinColumn(name = "id_booking_details", referencedColumnName = "id_booking_details")
    @JsonIgnore
    private BookingDetails bookingDetails;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
