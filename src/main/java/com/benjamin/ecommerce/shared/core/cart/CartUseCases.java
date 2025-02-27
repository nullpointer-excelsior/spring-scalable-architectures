package com.benjamin.ecommerce.shared.core.cart;

import com.benjamin.ecommerce.shared.core.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.shared.core.cart.models.Cart;
import com.benjamin.ecommerce.shared.core.cart.models.CartProduct;

import java.util.List;

public interface CartUseCases {
    Cart create(CreateCartRequest request);
    Cart updateProducts(Long cartId, List<CartProduct> products);
    Cart findById(Long id);
}
