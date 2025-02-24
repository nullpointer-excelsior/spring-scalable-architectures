package com.benjamin.ecommerce.shared.core.cart.dto;

import com.benjamin.ecommerce.shared.core.cart.models.CartProduct;
import com.benjamin.ecommerce.shared.core.cart.models.CartUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record CreateCartRequest(
        @Valid @NotNull CartUser user, @Valid @Size(min = 1) List<CartProduct> products) {}
