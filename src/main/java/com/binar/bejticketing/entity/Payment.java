package com.binar.bejticketing.entity;

import com.binar.bejticketing.utils.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "payments")
public class Payment {
    @Id
    @SequenceGenerator(name = "test_seq", sequenceName = "test_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq")
    @Column(name = "id_payment")
    private Long idPayment;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "payment")
    @JoinColumn(name = "id_passenger",referencedColumnName = "id_passenger")
    private Passenger passenger;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "is_paying")
    private boolean isPaying = false;

    @OneToMany(mappedBy = "payment")
    @JsonIgnore
    private List<BookingDetails> bookingDetails;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
