package com.benjamin.ecommerce.shipping.models;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record Shipping(@NotNull Long id,@NotNull Long purchaseId, @NotNull Delivery delivery, LocalDateTime shippedAt, LocalDateTime deliveredAt) {}
