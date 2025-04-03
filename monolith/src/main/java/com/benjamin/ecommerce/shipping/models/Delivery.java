package com.benjamin.ecommerce.shipping.models;

import jakarta.validation.constraints.NotEmpty;

public record Delivery(@NotEmpty String id, @NotEmpty String fullname, @NotEmpty String address, @NotEmpty String city, DeliveryOption option) {}
