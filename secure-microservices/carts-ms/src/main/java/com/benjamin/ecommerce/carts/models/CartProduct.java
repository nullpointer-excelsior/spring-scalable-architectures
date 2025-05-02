package com.benjamin.ecommerce.carts.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record CartProduct(
    @NotEmpty String sku,
    @NotEmpty String name,
    @NotEmpty String brand,
    String image,
    @Positive Double price,
    @Positive Integer quantity) {}
