package com.benjamin.ecommerce.shared.core.cart.dto;

import com.benjamin.ecommerce.shared.core.cart.models.CartProduct;
import jakarta.validation.Valid;
import java.util.List;

public record UpdateProductsRequest(@Valid List<CartProduct> products) {}
