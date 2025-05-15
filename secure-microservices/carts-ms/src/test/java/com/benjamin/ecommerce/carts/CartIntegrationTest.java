package com.benjamin.ecommerce.carts;

import com.benjamin.ecommerce.CheckoutApplication;
import com.benjamin.ecommerce.carts.entities.CartEntity;
import com.benjamin.ecommerce.carts.entities.CartProductEntity;
import com.benjamin.ecommerce.carts.entities.CartUserEntity;
import com.benjamin.ecommerce.carts.repositories.CartProductRepository;
import com.benjamin.ecommerce.carts.repositories.CartRepository;
import com.benjamin.ecommerce.carts.repositories.CartUserRepository;
import com.benjamin.ecommerce.shared.TestUtils;
import com.benjamin.ecommerce.carts.dto.CreateCartRequest;
import com.benjamin.ecommerce.carts.dto.UpdateProductsRequest;
import com.benjamin.ecommerce.carts.models.CartProduct;
import com.benjamin.ecommerce.carts.models.CartUser;
import com.benjamin.ecommerce.shared.objectmother.CreateCartRequestMother;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CheckoutApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class CartIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    CartUserRepository cartUserRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartProductRepository cartProductRepository;

    @AfterEach
    void tearDown() {
        this.cartRepository.deleteAll();
    }

    @Test
    @DisplayName("GIVEN no cart in database WHEN POST /carts with CreateCartRequest valid THEN response CartResponse OK")
    void createCartTest() throws Exception {

        var createCartRequest = CreateCartRequestMother.builder()
                .withUser(new CartUser(1L, "john@company.com"))
                .withProducts(new CartProduct("1", "guitar", "bc rich", "warlock.jpg",1000.0, 1))
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/carts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(createCartRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()")
                        .value(createCartRequest.products().size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(1000.0));
        ;
    }

    @Test
    @DisplayName(
            "GIVEN user not registered controller WHEN POST /carts with CreateCartRequest with user not registered THEN save new User")
    void createCartAndSaveNewUserTest() throws Exception {

        var createCartRequest = CreateCartRequestMother.builder()
                .withUser(new CartUser(1L, "john@company.com"))
                .withProducts(new CartProduct("1", "guitar", "bc rich", "warlock.jpg",1000.0, 1))
                .build();

        assertThat(this.cartUserRepository.count()).isEqualTo(0);

        mvc.perform(MockMvcRequestBuilders.post("/carts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(createCartRequest)))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(this.cartUserRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName(
            "GIVEN user registered WHEN POST /carts with CreateCartRequest with user registered THEN not save new User")
    void createCartAndDonNotSaveNewUserTest() throws Exception {

        var persistedUser = this.cartUserRepository.save(
                CartUserEntity.builder().email("persisted@company.com").build());

        var createCartRequest = CreateCartRequestMother.builder()
                .withUser(new CartUser(persistedUser.getId(), persistedUser.getEmail()))
                .withProducts(new CartProduct("1", "guitar", "bc rich", "warlock.jpg",1000.0, 1))
                .build();

        assertThat(this.cartUserRepository.count()).isEqualTo(1);

        mvc.perform(MockMvcRequestBuilders.post("/carts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(createCartRequest)))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(this.cartUserRepository.count()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DisplayName(
            "GIVEN cart created WHEN PUT /carts/{id}/products with valid ProductModel list THEN response CartResponse OK")
    public void updateCartProductsValidTest() throws Exception {
        var savedCart = createCart();
        var request = new UpdateProductsRequest(List.of(
                CartProduct.builder()
                        .sku("1")
                        .name("p1")
                        .price(1000.0)
                        .brand("b")
                        .image("image.jpg")
                        .quantity(1)
                        .build(),
                CartProduct.builder()
                        .sku("2")
                        .name("p2")
                        .brand("b")
                        .image("image.jpg")
                        .price(1000.0)
                        .quantity(1)
                        .build(),
                CartProduct.builder()
                        .sku("3")
                        .name("p3")
                        .brand("b")
                        .image("image.jpg")
                        .price(1000.0)
                        .quantity(1)
                        .build()));

        mvc.perform(MockMvcRequestBuilders.put("/carts/{id}/products", savedCart.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedCart.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()")
                        .value(request.products().size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(3000.0));
    }

    @Test
    @Transactional
    @DisplayName(
            "GIVEN cart created WHEN PATCH /carts/{id}/products/{sku} with valid ProductModel THEN response CartProduct OK")
    public void updateCartProductValidTest() throws Exception {
        var savedCart = createCart();
        var productToUpdate = savedCart.getProducts().getFirst();

        assertThat(productToUpdate.getName()).isNotEqualTo("name-updated");
        assertThat(productToUpdate.getBrand()).isNotEqualTo("brand-updated");

        var request = CartProduct.builder()
                .sku(productToUpdate.getSku())
                .name("name-updated")
                .brand("brand-updated")
                .image("image.jpg")
                .price(1000.0)
                .quantity(1)
                .build();

        mvc.perform(MockMvcRequestBuilders.patch("/carts/{id}/products/{sku}", savedCart.getId(), request.sku())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(request.sku()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(request.brand()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(request.price()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(request.quantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value(request.image()));
    }

    @Test
    @Transactional
    @DisplayName(
            "GIVEN cart created WHEN DELETE /carts/{id}/products/{sku} THEN response OK")
    public void deleteCartProductValidTest() throws Exception {
        var savedCart = createCart();
        var productToDelete = savedCart.getProducts().getFirst();

        assertThat(savedCart.getProducts().size()).isEqualTo(2L);

        mvc.perform(MockMvcRequestBuilders.delete("/carts/{id}/products/{sku}", savedCart.getId(), productToDelete.getSku()))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("GIVEN no cart created WHEN PUT /carts/999/products with cart not found THEN response NotFound")
    void updateProductWithCartNotFoundTest() throws Exception {
        var request = new UpdateProductsRequest(List.of(CartProduct.builder()
                .sku("1")
                .name("p1")
                .brand("b")
                .image("image.jpg")
                .price(1000.0)
                .quantity(1)
                .build()));
        mvc.perform(MockMvcRequestBuilders.put("/carts/999/products")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(request)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @DisplayName("GIVEN cart created WHEN GET /carts/1 THEN cart found by Id")
    void cartFindById() throws Exception {
        var savedCart = createCart();

        mvc.perform(MockMvcRequestBuilders.get("/carts/" + savedCart.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()")
                        .value(savedCart.getProducts().size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(2000.0));
    }

    @ParameterizedTest
    @DisplayName("GIVEN caty created WHEN POST /carts with invalid CreateCartRequest THEN response BadRequest")
    @MethodSource("invalidCartRequests")
    void createInvalidCartTest(CreateCartRequest request) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/carts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    static Stream<CreateCartRequest> invalidCartRequests() {
        return Stream.of(
                CreateCartRequestMother.builder()
                        .withProducts(new CartProduct("1111", "guitar", "jackson", null,-100.0, 1))
                        .build(),
                CreateCartRequestMother.builder()
                        .withProducts(new CartProduct("", "guitar", "jackson", "image.jpg", 100.0, 1))
                        .build(),
                CreateCartRequestMother.builder()
                        .withProducts(new CartProduct("1111", "","jackson", "image.jpg", 100.0, 1))
                        .build(),
                CreateCartRequestMother.builder()
                        .withProducts(new CartProduct("1111", "","", "image.jpg", 100.0, 1))
                        .build(),
                CreateCartRequestMother.builder()
                        .withProducts(Collections.emptyList())
                        .build(),
                CreateCartRequestMother.builder()
                        .withUser(new CartUser(1111L, "juan.com"))
                        .build(),
                CreateCartRequestMother.builder()
                        .withUser(new CartUser(null, "xxx@company.com"))
                        .build());
    }

    @Transactional
    @ParameterizedTest
    @DisplayName(
            "GIVEN cart created WHEN PUT /carts/{id}/products with invalid ProductModel list THEN response BadRequest")
    @MethodSource("invalidProductRequests")
    void updateCartProductsInvalidTest(UpdateProductsRequest request) throws Exception {
        var savedCart = createCart();
        mvc.perform(MockMvcRequestBuilders.put("/carts/{id}/products", savedCart.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    static Stream<UpdateProductsRequest> invalidProductRequests() {
        return Stream.of(
                new UpdateProductsRequest(List.of(new CartProduct("", "guitar","jackson", "image.jpg", 100.0, 1))), // SKU vacío
                new UpdateProductsRequest(List.of(new CartProduct("1111", "","jackson", "image.jpg", 100.0, 1))), // Nombre vacío
                new UpdateProductsRequest(List.of(new CartProduct("1111", "guitar","jackson", "image.jpg", -100.0, 1))), // Precio negativo
                new UpdateProductsRequest(List.of(new CartProduct("1111", "guitar", "jackson", "image.jpg",100.0, 0))), // Cantidad no positiva
                new UpdateProductsRequest(List.of(new CartProduct("1111", "guitar", "", "image.jpg",100.0, 0)))
                );
    }

    public CartEntity createCart() {
        var user = this.cartUserRepository.save(
                CartUserEntity.builder().email("user@company.com").build());
        var products = List.of(
                CartProductEntity.builder()
                        .sku("1")
                        .name("p1")
                        .price(1000.0)
                        .brand("b1")
                        .image("i1")
                        .quantity(1)
                        .build(),
                CartProductEntity.builder()
                        .sku("2")
                        .name("p2")
                        .brand("b2")
                        .image("i2")
                        .price(1000.0)
                        .quantity(1)
                        .build());
        return this.cartRepository.save(CartEntity.builder()
                .amount(2000.0)
                .user(user)
                .products(products)
                .build());
    }
}
