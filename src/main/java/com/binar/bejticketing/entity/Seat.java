package com.binar.bejticketing.entity;

import com.binar.bejticketing.utils.SeatUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeat;

    @Column(name = "number_seat")
    private String numberSeat;

    @Column(name = "state_seat")
    private SeatUtils stateSeat;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_plane_detail", referencedColumnName = "id_plane_details")
    private PlaneDetails planeDetails;

    @JoinColumn(name = "id_booking_details", referencedColumnName = "id_booking_details")
    @OneToOne
    @JsonIgnore
    private BookingDetails bookingDetails;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at",nullable = false,  updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public Seat(long idSeat, SeatUtils seatState, String numberSeat, Date createdAt, Date updatedAt) {
        this.idSeat = idSeat;
        this.stateSeat = seatState;
        this.numberSeat = numberSeat;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
