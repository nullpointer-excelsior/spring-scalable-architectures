package com.benjamin.ecommerce.purchase.entities;

import com.benjamin.ecommerce.purchase.models.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "purchase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private PurchaseRequestEntity purchaseRequest;

}
