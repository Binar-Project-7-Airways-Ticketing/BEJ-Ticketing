package com.binar.bejticketing.entity;

import com.binar.bejticketing.utils.Gender;
import com.binar.bejticketing.utils.SpecialRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passenger")
    private Long idPassenger;

    @Column(name = "first_name")
    @NotBlank(message = "First Name Not Null")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last Name Not Null")
    private String lastName;

    @Column(name = "contact_number")
    @NotBlank(message = "Contact Number Not Null")
    private String contactNumber;

    @Column(name = "email")
    @NotBlank(message = "Email Not Null")
    @Email
    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "nationality")
    @NotBlank(message = "Nationality Not Null")
    private String nationality;

    @Column(name = "special_request")
    @Enumerated(EnumType.ORDINAL)
    private SpecialRequest specialRequest;

    @OneToOne(mappedBy = "passenger")
    private AgeCategory ageCategory;

    @JoinColumn(name = "id_booking", referencedColumnName = "id_booking")
    @OneToOne
    @JsonIgnore
    private Booking booking;

    @OneToOne
    @JoinColumn(name = "id_payment", referencedColumnName = "id_payment")
    @JsonIgnore
    private Payment payment;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
