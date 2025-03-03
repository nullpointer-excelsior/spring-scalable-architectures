package com.benjamin.ecommerce.products;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateProductQuantity(@NotEmpty String sku, @NotNull Integer quantity) {}
