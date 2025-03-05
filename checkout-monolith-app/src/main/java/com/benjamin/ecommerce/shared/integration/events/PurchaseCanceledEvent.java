package com.benjamin.ecommerce.shared.integration.events;

import com.benjamin.ecommerce.purchase.models.Purchase;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class PurchaseCanceledEvent extends Event<Purchase> {
    public PurchaseCanceledEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull Purchase payload) {
        super(id, createdAt, payload);
    }

    public PurchaseCanceledEvent(Purchase payload) {
        super(payload);
    }
}
