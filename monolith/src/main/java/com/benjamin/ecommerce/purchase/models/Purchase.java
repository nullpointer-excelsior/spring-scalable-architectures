package com.benjamin.ecommerce.purchase.models;

import com.benjamin.ecommerce.order.models.Order;
import com.benjamin.ecommerce.payment.models.Payment;
import com.benjamin.ecommerce.shipping.models.Delivery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record Purchase(@NotNull Long id, @Valid Order order, @Valid Payment payment, @Valid Delivery delivery, PurchaseStatus status) {}
