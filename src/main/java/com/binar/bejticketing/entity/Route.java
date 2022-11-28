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
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route")
    private Long idRoute;

    @JoinColumn(name = "id_airport_start", referencedColumnName = "id_airport_start")
    @ManyToOne
    private AirportStart airportStart;

    @JoinColumn(name = "id_airport_final", referencedColumnName = "id_airport_final")
    @ManyToOne
    private AirportStart airportFinal;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
