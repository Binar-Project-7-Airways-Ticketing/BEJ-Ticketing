package com.binar.bejticketing.entity;

import com.binar.bejticketing.dto.PlaneClassEnum;
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
    private String planeClass;

    @Column(name = "price")
    private Long price;


    @ManyToMany
    @JsonIgnore
    private List<Plane> plane;

    @Column
    @OneToMany
    @JsonIgnore
    private List<Seat> seat;

    @OneToMany
    @JsonIgnore
    private List<Luggage> luggage;


}
