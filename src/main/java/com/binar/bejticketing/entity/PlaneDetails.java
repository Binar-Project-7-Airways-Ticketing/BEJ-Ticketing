package com.binar.bejticketing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column
    @JsonIgnore
    private List<Luggage> luggage;


}
