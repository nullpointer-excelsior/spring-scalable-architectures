package com.benjamin.ecommerce.cart;

import com.benjamin.ecommerce.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.cart.models.Cart;
import com.benjamin.ecommerce.cart.models.CartProduct;

import java.util.List;

public interface CartUseCases {
    Cart create(CreateCartRequest request);
    Cart updateProducts(Long cartId, List<CartProduct> products);
    Cart findById(Long id);
    CartProduct updateProduct(Long cartId, String sku, CartProduct product);
    void deleteProduct(Long cartId, String sku);
}
