package com.benjamin.ecommerce.shared.integration.events;

import com.benjamin.ecommerce.purchase.dto.CreatePayment;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class CreatePaymentEvent extends Event<CreatePayment> {

    public CreatePaymentEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull CreatePayment payload) {
        super(id, createdAt, payload);
    }

    public CreatePaymentEvent(CreatePayment payload) {
        super(payload);
    }
}
