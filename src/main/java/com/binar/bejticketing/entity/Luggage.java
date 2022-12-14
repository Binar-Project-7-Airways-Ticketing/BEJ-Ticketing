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
@Table(name = "Luggage")
public class Luggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_luggage")
    private Long idLuggage;

    @Column(name = "capacity")
    private BigInteger capacity;

    @Column(name = "price")
    private BigInteger price;

    @Column(name = "is_ready")
    private boolean isReady = true;

    @JoinColumn(name = "id_booking_details", referencedColumnName = "id_booking_details")
    @OneToOne
    @JsonIgnore
    private BookingDetails bookingDetails;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_plane_detail", referencedColumnName = "id_plane_details")
    private PlaneDetails planeDetails;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
