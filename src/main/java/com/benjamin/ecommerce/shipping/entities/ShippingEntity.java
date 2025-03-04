package com.benjamin.ecommerce.shipping.entities;

import com.benjamin.ecommerce.shipping.models.ShippingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shippings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ShippingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long purchaseId;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @OneToOne(mappedBy = "shipping", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DeliveryEntity delivery;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime shippedAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deliveredAt;
}
