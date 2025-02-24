package com.benjamin.ecommerce.shared.core.cart.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record CartProduct(
    @NotEmpty String sku,
    @NotEmpty String name,
    @Positive Double price,
    @Positive Integer quantity) {}
