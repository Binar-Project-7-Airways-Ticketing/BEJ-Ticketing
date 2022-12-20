package com.binar.bejticketing.entity;

import com.binar.bejticketing.utils.Gender;
import com.binar.bejticketing.utils.SpecialRequest;
import com.binar.bejticketing.utils.TitleUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_passenger")
    private Long idPassenger;

    @Column(name = "title")
    private TitleUser titleUser;

    @Column(name = "first_name")
    @NotBlank(message = "First Name Not Null")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last Name Not Null")
    private String lastName;

    @Column(name = "contact_number")
    @NotBlank(message = "Contact Number Not Null")
    private String contactNumber;

    @JsonFormat(pattern = "MM/dd/yyyy")
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "genders")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "nationality")
    @NotBlank(message = "Nationality Not Null")
    private String nationality;

    @Column(name = "special_requests")
    @Enumerated(EnumType.STRING)
    private SpecialRequest specialRequest;

    @Column(name = "passport")
    private String passport;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_age_category", referencedColumnName = "id_category")
    private AgeCategory ageCategory;

    @JoinColumn(name = "id_booking_detail")
    @OneToOne
    @JsonIgnore
    private BookingDetails bookingDetails;

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
