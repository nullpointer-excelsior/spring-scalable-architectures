package com.benjamin.ecommerce.cart.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CartUser(@NotNull Long id, @Email String email) {}
