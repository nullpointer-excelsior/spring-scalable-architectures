package com.benjamin.ecommerce.shared.core.cart.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record Cart(
    @NotEmpty String id,
    @Valid CartUser user,
    @Size(min = 1) List<CartProduct> products,
    @Positive Double amount) {}
