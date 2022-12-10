package com.binar.bejticketing.entity;

import com.binar.bejticketing.utils.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @JsonFormat(pattern = "MM/dd-yyyy")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "email")
    @Email(message = "Value must be Email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "last_login_date")
    private LocalDate lastLoginDate;

    @Column(name = "picture_url")
    private String pictureUrl="http://res.cloudinary.com/dwncupcal/image/upload/be781f4f-99d1-4dc4-925d-58072f4de335";

    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private Role role;

    @OneToMany(targetEntity = Booking.class, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;

    @OneToOne(targetEntity = History.class)
    @JsonIgnore
    private History history;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
