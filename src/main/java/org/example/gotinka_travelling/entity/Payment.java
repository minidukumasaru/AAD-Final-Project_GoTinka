package org.example.gotinka_travelling.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private Double amount;
    private String paymentMethod; // CREDIT_CARD, PAYPAL
    private String transactionId;
    private Boolean paymentStatus;

}