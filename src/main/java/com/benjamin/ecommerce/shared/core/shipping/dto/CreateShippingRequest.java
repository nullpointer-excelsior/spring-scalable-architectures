package com.benjamin.ecommerce.shared.core.shipping.dto;

import com.benjamin.ecommerce.shared.core.shipping.models.DeliveryOption;
import jakarta.validation.constraints.NotEmpty;

public record CreateShippingRequest(
    @NotEmpty String fullname,
    @NotEmpty String address,
    @NotEmpty String city,
    DeliveryOption option) {}
