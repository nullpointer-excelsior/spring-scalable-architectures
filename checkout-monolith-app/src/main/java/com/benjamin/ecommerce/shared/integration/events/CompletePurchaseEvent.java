package com.benjamin.ecommerce.shared.integration.events;

import com.benjamin.ecommerce.purchase.dto.CompletePurchase;
import com.benjamin.ecommerce.shared.integration.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class CompletePurchaseEvent extends Event<CompletePurchase> {

    public CompletePurchaseEvent(@NotEmpty String id, @NotNull @PastOrPresent LocalDateTime createdAt, @Valid @NotNull CompletePurchase payload) {
        super(id, createdAt, payload);
    }

    public CompletePurchaseEvent(CompletePurchase payload) {
        super(payload);
    }
}
