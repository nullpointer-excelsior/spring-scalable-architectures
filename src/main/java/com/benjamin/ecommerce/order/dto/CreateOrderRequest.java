package com.benjamin.ecommerce.order.dto;

import com.benjamin.ecommerce.order.dto.models.OrderProduct;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateOrderRequest(
    @NotNull @Size(min = 1) List<OrderProduct> products, @Positive @NotNull Double amount) {}
