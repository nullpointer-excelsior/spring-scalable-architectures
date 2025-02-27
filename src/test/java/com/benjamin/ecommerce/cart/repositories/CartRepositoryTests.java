package com.benjamin.ecommerce.cart.repositories;

import com.benjamin.ecommerce.cart.entities.CartEntity;
import com.benjamin.ecommerce.cart.entities.CartProductEntity;
import com.benjamin.ecommerce.cart.entities.CartUserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest(
        properties = {
            "spring.datasource.url=jdbc:h2:mem:testdb",
            "spring.jpa.hibernate.ddl-auto=create-drop",
        },
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CartRepository.class)
)
public class CartRepositoryTests {

    @Autowired
    CartRepository cartRepository;
    CartEntity cart;


    @BeforeEach
    void setup() {
        cart = new CartEntity();
        cartRepository.save(cart);
        var user = CartUserEntity.builder()
                .email("user@company.com")
                .build();
        var product = CartProductEntity.builder()
                .sku("1111")
                .name("guitar")
                .price(1000.0)
                .quantity(1)
                .cart(cart)
                .build();
        var products = new ArrayList<>(List.of(product));
        cart.setAmount(1000.0);
        cart.setUser(user);
        cart.setProducts(products);
        cartRepository.save(cart);
    }

    @AfterEach
    void tearDown() {
        cartRepository.delete(cart);
    }

    @Test
    @DisplayName("GIVEN cart WHEN saved THEN can be found by id")
    void givenCart_whenSaved_thenCanBeFoundById() {
        var savedCart = cartRepository.findById(cart.getId()).orElse(null);
        assertThat(savedCart).isNotNull();
        assertThat(savedCart.getAmount()).isEqualTo(cart.getAmount());
        assertThat(savedCart.getProducts()).hasSize(cart.getProducts().size());
    }


}
