package com.benjamin.ecommerce.shared.core.purchase.dto;

import com.benjamin.ecommerce.shared.core.order.models.OrderProduct;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateOrder(
        @NotNull Long purchaseId, @NotNull @Size(min = 1) List<OrderProduct> products, @Positive @NotNull Double amount) {}
