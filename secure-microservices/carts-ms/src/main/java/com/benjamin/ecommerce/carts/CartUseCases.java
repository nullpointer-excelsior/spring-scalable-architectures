package com.benjamin.ecommerce.carts;

import com.benjamin.ecommerce.carts.dto.CreateCartRequest;
import com.benjamin.ecommerce.carts.models.Cart;
import com.benjamin.ecommerce.carts.models.CartProduct;

import java.util.List;

public interface CartUseCases {
    Cart create(CreateCartRequest request);
    Cart updateProducts(Long cartId, List<CartProduct> products);
    Cart findById(Long id);
    CartProduct updateProduct(Long cartId, String sku, CartProduct product);
    void deleteProduct(Long cartId, String sku);
}
