package com.benjamin.ecommerce.shared.core.cart.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CartUser(@NotEmpty String id, @Email String email) {}
