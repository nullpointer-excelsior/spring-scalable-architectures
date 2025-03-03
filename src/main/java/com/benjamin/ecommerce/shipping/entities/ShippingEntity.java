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
public class ShippingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @OneToOne(mappedBy = "shipping", cascade = CascadeType.ALL)
    private DeliveryEntity delivery;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime shippedAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deliveredAt;
}
