package com.benjamin.ecommerce.products.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Product(
    @NotEmpty String sku,
    @NotEmpty String name,
    @NotEmpty String brand,
    String image,
    @Positive Double price,
    @NotNull Integer quantity) {}
