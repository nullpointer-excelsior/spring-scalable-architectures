package com.benjamin.ecommerce.shared.core.purchase.events;

import com.benjamin.ecommerce.shared.core.order.dto.CreateOrderRequest;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class OrderRequestEvent extends Event<CreateOrderRequest> {
    public OrderRequestEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull CreateOrderRequest payload) {
        super(id, createdAt, payload);
    }

    public OrderRequestEvent(CreateOrderRequest payload) {
        super(payload);
    }
}
