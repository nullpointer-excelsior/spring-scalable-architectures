package com.benjamin.ecommerce.cart.dto;

import com.benjamin.ecommerce.cart.models.CartProduct;
import jakarta.validation.Valid;
import java.util.List;

public record UpdateProductsRequest(@Valid List<CartProduct> products) {}
