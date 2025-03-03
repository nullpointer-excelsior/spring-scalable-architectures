package com.benjamin.ecommerce.order.dto.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record Order(
    @NotEmpty String id,
    @NotNull Long purchaseId,
    @Size(min = 1) List<OrderProduct> products,
    @Positive Double amount,
    OrderStatus status) {}
