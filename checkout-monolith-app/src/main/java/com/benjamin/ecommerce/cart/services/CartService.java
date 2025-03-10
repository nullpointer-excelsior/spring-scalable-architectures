package com.benjamin.ecommerce.cart.services;

import com.benjamin.ecommerce.cart.entities.CartEntity;
import com.benjamin.ecommerce.cart.entities.CartUserEntity;
import com.benjamin.ecommerce.cart.mappers.CartMapper;
import com.benjamin.ecommerce.cart.mappers.CartProductMapper;
import com.benjamin.ecommerce.cart.repositories.CartProductRepository;
import com.benjamin.ecommerce.cart.repositories.CartRepository;
import com.benjamin.ecommerce.cart.repositories.CartUserRepository;
import com.benjamin.ecommerce.cart.CartUseCases;
import com.benjamin.ecommerce.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.cart.models.Cart;
import com.benjamin.ecommerce.cart.models.CartProduct;
import com.benjamin.ecommerce.cart.models.CartUser;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
                .products(cartProductMapper.toUnPersistedEntities(request.products()))
                .amount(amount)
                .build();
        cartRepository.save(cart);
        return cartMapper.toModel(cart);
    }

    public Cart updateProducts(Long cartId, List<CartProduct> products) {
        var cart = cartRepository.findById(cartId).orElseThrow(() -> new NoSuchElementException("Cart not found"));
        cart.setProducts(cartProductMapper.toUnPersistedEntities(products));
        cart.setAmount(calculateCartAmount(products));
        cartRepository.save(cart);
        return cartMapper.toModel(cart);
    }

    @Override
    public CartProduct updateProduct(Long cartId, String sku, CartProduct product) {
        var entity = cartProductRepository.findByCartIdAndSku(cartId, sku).orElseThrow(() -> new NoSuchElementException("ProductCart not found"));;
        entity.setName(product.name());
        entity.setPrice(product.price());
        entity.setBrand(product.brand());
        entity.setImage(product.image());
        entity.setQuantity(product.quantity());
        cartProductRepository.save(entity);
        return cartProductMapper.toModel(entity);
    }

    @Transactional
    @Override
    public void deleteProduct(Long cartId, String sku) {
       cartProductRepository.deleteByCartIdAndSku(cartId, sku);
    }

    @Override
    public Cart findById(Long id) {
        return this.cartRepository.findById(id)
                .map(cartMapper::toModel)
                .orElseThrow(() -> new NoSuchElementException("Cart not found"));
    }

    private CartUserEntity getExistingUserOrCreateUser(CartUser user) {
        var foundUser = cartUserRepository.findById(user.id());
        return foundUser.orElseGet(() -> cartUserRepository.save(
                CartUserEntity.builder().email(user.email()).build()));
    }

    private Double calculateCartAmount(List<CartProduct> products) {
        return products.stream().map(p -> p.price() * p.quantity()).reduce(0.0, Double::sum);
    }
}
