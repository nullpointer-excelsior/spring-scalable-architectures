package com.benjamin.ecommerce.shared.core.purchase.dto;

import com.benjamin.ecommerce.shared.core.order.dto.CreateOrderRequest;
import com.benjamin.ecommerce.shared.core.payment.dto.CreatePaymentRequest;
import com.benjamin.ecommerce.shared.core.shipping.dto.CreateShippingRequest;
import jakarta.validation.Valid;
import lombok.Builder;

@Builder
public record CreatePurchaseRequest(
        @Valid CreateOrderRequest order, @Valid CreatePaymentRequest payment, @Valid CreateShippingRequest shipping) {}
