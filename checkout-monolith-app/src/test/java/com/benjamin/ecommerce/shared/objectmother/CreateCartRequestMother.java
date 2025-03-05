package com.benjamin.ecommerce.shared.objectmother;

import com.benjamin.ecommerce.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.cart.models.CartProduct;
import com.benjamin.ecommerce.cart.models.CartUser;

import java.util.Collections;
import java.util.List;

public class CreateCartRequestMother {

    public static CreateCartRequestBuilder builder() {
        return new CreateCartRequestBuilder();
    }

    public static class CreateCartRequestBuilder {
        private CartUser user = new CartUser(1L, "john@company.com");
    private List<CartProduct> products = List.of(new CartProduct("1111", "guitar", 1000.0, 1));

        public CreateCartRequestBuilder withUser(CartUser user) {
            this.user = user;
            return this;
        }

        public CreateCartRequestBuilder withProducts(CartProduct...products) {
            this.products = List.of(products);
            return this;
        }

        public CreateCartRequestBuilder withProducts(List<CartProduct> products) {
            this.products = products;
            return this;
        }

        public CreateCartRequest build() {
            return new CreateCartRequest(user, products);
        }
    }

    public static CreateCartRequest valid() {
        return builder().build();
    }

    public static CreateCartRequest withEmptyProductList() {
        return builder().withProducts(Collections.emptyList()).build();
    }

    public static CreateCartRequest withCustomUser(CartUser user) {
        return builder().withUser(user).build();
    }

    public static CreateCartRequest withCustomProducts(List<CartProduct> products) {
        return builder().withProducts(products).build();
    }
}

