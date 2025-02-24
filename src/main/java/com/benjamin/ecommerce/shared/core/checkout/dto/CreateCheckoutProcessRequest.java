package com.benjamin.ecommerce.shared.core.checkout.dto;

import com.benjamin.ecommerce.shared.core.cart.models.Cart;
import com.benjamin.ecommerce.shared.core.payment.dto.CreatePaymentRequest;
import com.benjamin.ecommerce.shared.core.shipping.dto.CreateShippingRequest;
import jakarta.validation.Valid;
import lombok.Builder;

@Builder
public record CreateCheckoutProcessRequest(
    @Valid Cart cart, @Valid CreatePaymentRequest payment, @Valid CreateShippingRequest shipping) {}
