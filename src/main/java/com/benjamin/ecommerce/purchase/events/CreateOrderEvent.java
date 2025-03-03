package com.benjamin.ecommerce.purchase.events;

import com.benjamin.ecommerce.purchase.dto.CreateOrder;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class CreateOrderEvent extends Event<CreateOrder> {
    public CreateOrderEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull CreateOrder payload) {
        super(id, createdAt, payload);
    }

    public CreateOrderEvent(CreateOrder payload) {
        super(payload);
    }
}
