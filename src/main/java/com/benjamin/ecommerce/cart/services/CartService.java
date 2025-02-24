package com.benjamin.ecommerce.cart.services;

import com.benjamin.ecommerce.shared.core.cart.CartUseCases;
import com.benjamin.ecommerce.shared.core.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.shared.core.cart.models.Cart;
import com.benjamin.ecommerce.shared.core.cart.models.CartProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements CartUseCases {

    public Cart create(CreateCartRequest request) {
        throw new UnsupportedOperationException("not implemented");
    }

    public Cart updateProducts(String cartId, List<CartProduct> products) {
        throw new UnsupportedOperationException("not implemented");
    }
}
