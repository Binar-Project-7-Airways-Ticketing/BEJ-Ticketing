package com.binar.bejticketing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plane")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plane")
    private Long idPlane;

    @Column(name = "plane_type")
    private String planeType;

    @Column(name = "baggage_capacity")
    private Integer baggageCapacity;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinColumn(name = "id_plane_details", referencedColumnName = "id_plane_details")
    private List<PlaneDetails> planeClass;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
