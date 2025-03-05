package com.benjamin.ecommerce.purchase.dto;

import com.benjamin.ecommerce.shipping.models.DeliveryOption;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateShipping(
        @NotNull Long purchaseId,
        @NotEmpty String fullname,
        @NotEmpty String address,
        @NotEmpty String city,
        DeliveryOption option
) {
}
