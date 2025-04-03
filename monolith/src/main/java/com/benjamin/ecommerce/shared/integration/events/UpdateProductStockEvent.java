package com.benjamin.ecommerce.shared.integration.events;

import com.benjamin.ecommerce.products.dto.UpdateProductQuantity;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateProductStockEvent extends Event<List<UpdateProductQuantity>> {
    public UpdateProductStockEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull List<UpdateProductQuantity> payload) {
        super(id, createdAt, payload);
    }

    public UpdateProductStockEvent(List<UpdateProductQuantity> payload) {
        super(payload);
    }
}
