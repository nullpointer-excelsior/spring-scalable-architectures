package com.benjamin.ecommerce.shared.core.purchase.events;

import com.benjamin.ecommerce.shared.core.payment.dto.CreatePaymentRequest;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class PaymentRequestEvent extends Event<CreatePaymentRequest> {

    public PaymentRequestEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull CreatePaymentRequest payload) {
        super(id, createdAt, payload);
    }

    public PaymentRequestEvent(CreatePaymentRequest payload) {
        super(payload);
    }
}
