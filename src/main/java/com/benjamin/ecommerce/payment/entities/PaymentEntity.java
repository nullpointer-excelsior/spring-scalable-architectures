package com.benjamin.ecommerce.payment.entities;

import com.benjamin.ecommerce.payment.models.PaymentMethod;
import com.benjamin.ecommerce.payment.models.PaymentStatus;
import jakarta.validation.constraints.Positive;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long purchaseId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Positive
    @Column(nullable = false)
    private Double amount;
}
