package com.binar.bejticketing.entity;

import com.binar.bejticketing.utils.SeatUtils;
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

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
