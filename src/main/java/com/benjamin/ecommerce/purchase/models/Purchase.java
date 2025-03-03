package com.benjamin.ecommerce.purchase.models;

import com.benjamin.ecommerce.order.dto.models.Order;
import com.benjamin.ecommerce.payment.models.Payment;
import com.benjamin.ecommerce.shipping.models.Delivery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record Purchase(@NotEmpty String id, @Valid Order order, @Valid Payment payment, @Valid Delivery delivery, PurchaseStatus status) {}
