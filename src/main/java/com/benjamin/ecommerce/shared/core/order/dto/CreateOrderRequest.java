package com.benjamin.ecommerce.shared.core.order.dto;

import com.benjamin.ecommerce.shared.core.order.models.OrderProduct;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateOrderRequest(
    @NotNull @Size(min = 1) List<OrderProduct> products, @Positive @NotNull Double amount) {}
