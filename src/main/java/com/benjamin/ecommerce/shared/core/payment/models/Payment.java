package com.benjamin.ecommerce.shared.core.payment.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record Payment(
    @NotEmpty String id, PaymentMethod method, PaymentStatus status, @Positive Double amount) {}
