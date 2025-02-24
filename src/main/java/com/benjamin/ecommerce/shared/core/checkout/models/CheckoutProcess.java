package com.benjamin.ecommerce.shared.core.checkout.models;

import com.benjamin.ecommerce.shared.core.order.models.Order;
import com.benjamin.ecommerce.shared.core.payment.models.Payment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record CheckoutProcess(
    @NotEmpty String id,
    @Valid Order order,
    @Valid Payment payment,
    CheckoutProcessStatus status) {}
