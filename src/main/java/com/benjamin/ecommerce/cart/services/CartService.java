package com.benjamin.ecommerce.cart.services;

import com.benjamin.ecommerce.cart.entities.CartEntity;
import com.benjamin.ecommerce.cart.entities.CartUserEntity;
import com.benjamin.ecommerce.cart.mappers.CartMapper;
import com.benjamin.ecommerce.cart.mappers.CartProductMapper;
import com.benjamin.ecommerce.cart.repositories.CartProductRepository;
import com.benjamin.ecommerce.cart.repositories.CartRepository;
import com.benjamin.ecommerce.cart.repositories.CartUserRepository;
import com.benjamin.ecommerce.shared.core.cart.CartUseCases;
import com.benjamin.ecommerce.shared.core.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.shared.core.cart.models.Cart;
import com.benjamin.ecommerce.shared.core.cart.models.CartProduct;
import com.benjamin.ecommerce.shared.core.cart.models.CartUser;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Service
public class CartService implements CartUseCases {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private CartUserRepository cartUserRepository;

    @Autowired
    private CartProductMapper cartProductMapper;

    @Autowired
    private CartMapper cartMapper;

    @Transactional
    public Cart create(CreateCartRequest request) {
        var amount = this.calculateCartAmount(request.products());
        var user = this.getExistingUserOrCreateUser(request.user());
        var cart = CartEntity.builder()
                .user(user)
                .products(this.cartProductMapper.toUnPersistedEntities(request.products()))
                .amount(amount)
                .build();
        this.cartRepository.save(cart);
        return this.cartMapper.toModel(cart);
    }

    public Cart updateProducts(Long cartId, List<CartProduct> products) {
        var cart = this.cartRepository.findById(cartId).orElseThrow(() -> new NoSuchElementException("Cart not found"));
        cart.setProducts(this.cartProductMapper.toUnPersistedEntities(products));
        cart.setAmount(this.calculateCartAmount(products));
        this.cartRepository.save(cart);
        return this.cartMapper.toModel(cart);
    }

    @Override
    public Cart findById(Long id) {
        return this.cartRepository.findById(id)
                .map(this.cartMapper::toModel)
                .orElseThrow(() -> new NoSuchElementException("Cart not found"));
    }

    private CartUserEntity getExistingUserOrCreateUser(CartUser user) {
        var foundUser = this.cartUserRepository.findById(user.id());
        return foundUser.orElseGet(() -> this.cartUserRepository.save(
                CartUserEntity.builder().email(user.email()).build()));
    }

    private Double calculateCartAmount(List<CartProduct> products) {
        return products.stream().map(p -> p.price() * p.quantity()).reduce(0.0, Double::sum);
    }
}
