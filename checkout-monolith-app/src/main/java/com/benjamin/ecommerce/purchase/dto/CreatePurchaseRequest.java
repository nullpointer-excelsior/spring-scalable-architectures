package com.benjamin.ecommerce.purchase.dto;

import com.benjamin.ecommerce.order.dto.CreateOrderRequest;
import com.benjamin.ecommerce.payment.dto.CreatePaymentRequest;
import com.benjamin.ecommerce.shipping.dto.CreateShippingRequest;
import jakarta.validation.Valid;
import lombok.Builder;

@Builder
public record CreatePurchaseRequest(
        @Valid CreateOrderRequest order, @Valid CreatePaymentRequest payment, @Valid CreateShippingRequest shipping) {}
