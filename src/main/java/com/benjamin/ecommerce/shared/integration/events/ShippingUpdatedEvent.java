package com.benjamin.ecommerce.shared.integration.events;

import com.benjamin.ecommerce.shared.integration.Event;
import com.benjamin.ecommerce.shipping.models.Shipping;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class ShippingUpdatedEvent extends Event<Shipping> {
    public ShippingUpdatedEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull Shipping payload) {
        super(id, createdAt, payload);
    }

    public ShippingUpdatedEvent(Shipping payload) {
        super(payload);
    }
}
