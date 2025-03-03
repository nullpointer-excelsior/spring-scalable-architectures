package com.benjamin.ecommerce.payment.events;

import com.benjamin.ecommerce.payment.models.Payment;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class PaymentCreatedEvent extends Event<Payment> {
    public PaymentCreatedEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull Payment payload) {
        super(id, createdAt, payload);
    }

    public PaymentCreatedEvent(Payment payload) {
        super(payload);
    }
}
