package com.benjamin.ecommerce.shared.core.purchase.models;

import com.benjamin.ecommerce.shared.core.order.models.Order;
import com.benjamin.ecommerce.shared.core.payment.models.Payment;
import com.benjamin.ecommerce.shared.core.shipping.models.Delivery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record Purchase(@NotEmpty String id, @Valid Order order, @Valid Payment payment, @Valid Delivery delivery, PurchaseStatus status) {}
