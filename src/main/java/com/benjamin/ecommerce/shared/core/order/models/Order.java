package com.benjamin.ecommerce.shared.core.order.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record Order(
    @NotEmpty String id,
    @Size(min = 1) List<OrderProduct> products,
    @Positive Double amount,
    OrderStatus status) {}
