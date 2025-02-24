package com.benjamin.ecommerce.shared.core.payment.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Payment(
    @NotEmpty String id, PaymentMethod method, PaymentStatus status, @Positive Double amount) {}
