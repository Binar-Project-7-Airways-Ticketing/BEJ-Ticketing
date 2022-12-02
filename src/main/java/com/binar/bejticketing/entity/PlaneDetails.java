package com.binar.bejticketing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plane_details")
public class PlaneDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plane_details")
    private Long idPlaneClass;

    @Column(name = "plane_class_name")
    private String PlaneClassName;

    @OneToMany
    @JsonIgnore
    private List<Seat> seat;

    @OneToMany
    @JsonIgnore
    private List<Luggage> luggage;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
