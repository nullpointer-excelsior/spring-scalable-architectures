package com.benjamin.ecommerce.purchase.entities;

@Entity
@Table(name = "create_payment_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(columnDefinition = "jsonb")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode details;

    @Column(nullable = false)
    private Double amount;

    @Column(columnDefinition = "jsonb")
    private JsonNode order;

    @Column(columnDefinition = "jsonb")
    private JsonNode payment;

    @Column(columnDefinition = "jsonb")
    private JsonNode shipping;
}
