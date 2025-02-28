package com.benjamin.ecommerce.shared.core.purchase.dto;

import com.benjamin.ecommerce.shared.core.payment.models.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.Map;

@Builder
public record CreatePayment(
        @NotNull Long purchaseId,
        @NotNull PaymentMethod method,
        @NotNull Map<String, String> details,
        @Positive Double amount
) {}
