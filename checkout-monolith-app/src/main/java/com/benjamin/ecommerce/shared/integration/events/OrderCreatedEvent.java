package com.benjamin.ecommerce.shared.integration.events;

import com.benjamin.ecommerce.order.models.Order;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class OrderCreatedEvent extends Event<Order> {
    public OrderCreatedEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull Order payload) {
        super(id, createdAt, payload);
    }

    public OrderCreatedEvent(Order payload) {
        super(payload);
    }
}
