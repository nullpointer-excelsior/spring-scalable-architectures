package com.benjamin.ecommerce.shared.core.payment.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record Payment(
        @NotEmpty Long id, @NotNull Long purchaseId, PaymentMethod method, PaymentStatus status, @Positive Double amount) {}