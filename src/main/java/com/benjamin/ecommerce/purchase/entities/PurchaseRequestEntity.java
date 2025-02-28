package com.benjamin.ecommerce.purchase.entities;

import com.benjamin.ecommerce.shared.core.payment.models.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "purchase_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(nullable = false)
    private Double amount;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> paymentRequest;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> orderRequest;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> shippingRequest;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

}
