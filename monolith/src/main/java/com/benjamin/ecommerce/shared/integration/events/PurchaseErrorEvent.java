package com.benjamin.ecommerce.shared.integration.events;

import com.benjamin.ecommerce.purchase.dto.PurchaseError;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class PurchaseErrorEvent extends Event<PurchaseError> {
    public PurchaseErrorEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull PurchaseError payload) {
        super(id, createdAt, payload);
    }

    public PurchaseErrorEvent(PurchaseError payload) {
        super(payload);
    }
}
