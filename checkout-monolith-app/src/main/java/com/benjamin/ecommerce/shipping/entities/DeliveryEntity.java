package com.benjamin.ecommerce.shipping.entities;

import com.benjamin.ecommerce.shipping.models.DeliveryOption;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryOption option;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @OneToOne
    @JoinColumn(name = "shipping_id")
    private ShippingEntity shipping;

}
