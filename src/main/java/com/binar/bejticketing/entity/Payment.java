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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long idPayment;

    @OneToOne(mappedBy = "payment")
    private Passenger passenger;

    @Column(name = "payment_method")
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @Column(name = "is_paying")
    private boolean isPaying = false;

    @OneToMany(mappedBy = "payment")
    @JoinColumn(referencedColumnName = "id_booking_details", name = "id_booking_detail")
    @JsonIgnore
    private List<BookingDetails> booking;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
