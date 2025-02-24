package com.benjamin.ecommerce.shared.core.payment.dto;

import com.benjamin.ecommerce.shared.core.payment.models.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Map;

public record CreatePaymentRequest(
    @NotNull PaymentMethod method,
    @NotNull Map<String, String> details,
    @Positive Integer amount) {}
