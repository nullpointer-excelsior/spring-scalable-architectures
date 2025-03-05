package com.benjamin.ecommerce.shared.integration.events;

import com.benjamin.ecommerce.purchase.dto.CreateShipping;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class CreateShippingEvent extends Event<CreateShipping> {
    public CreateShippingEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull CreateShipping payload) {
        super(id, createdAt, payload);
    }

    public CreateShippingEvent(CreateShipping payload) {
        super(payload);
    }
}
