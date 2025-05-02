package com.benjamin.ecommerce.carts.dto;

import com.benjamin.ecommerce.carts.models.CartProduct;
import jakarta.validation.Valid;
import java.util.List;

public record UpdateProductsRequest(@Valid List<CartProduct> products) {}
