package com.benjamin.ecommerce.cart;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.benjamin.ecommerce.cart.controllers.CartRestController;
import com.benjamin.ecommerce.cart.services.CartService;
import com.benjamin.ecommerce.shared.TestUtils;
import com.benjamin.ecommerce.shared.core.cart.CartUseCases;
import com.benjamin.ecommerce.shared.core.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.shared.core.cart.dto.UpdateProductsRequest;
import com.benjamin.ecommerce.shared.core.cart.models.Cart;
import com.benjamin.ecommerce.shared.core.cart.models.CartProduct;
import com.benjamin.ecommerce.shared.core.cart.models.CartUser;
import com.benjamin.ecommerce.shared.objectmother.CreateCartRequestMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@WebMvcTest(CartRestController.class)
public class CartRestControllerTest {

  @Autowired MockMvc mvc;
  @MockitoBean
  CartUseCases cartServiceMock;

  @Test
  @DisplayName("WHEN POST /cart with CreateCartRequest valid THEN response CartResponse OK")
  public void createValidCartTest() throws Exception {

    var body =
        CreateCartRequestMother.builder()
            .withUser(new CartUser("1", "john@company.com"))
            .withProducts(new CartProduct("1111", "guitar", 1000.0, 1))
            .build();
    when(cartServiceMock.create(any()))
        .thenReturn(new Cart("1111", body.user(), body.products(), 1000.0));

    mvc.perform(
            MockMvcRequestBuilders.post("/cart")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(body)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.amount").exists());
  }

  @Test
  @DisplayName(
      "WHEN PUT /cart/{id}/products with valid ProductModel list THEN response CartResponse OK")
  public void updateCartProductsValidTest() throws Exception {
    var cartId = "1111";
    var products =
        List.of(
            new CartProduct("1111", "guitar", 1000.0, 1),
            new CartProduct("2222", "piano", 2000.0, 1));
    var request = new UpdateProductsRequest(products);
    var response =
        new Cart(cartId, new CartUser("1", "john@company.com"), products, 1000.0);

    when(cartServiceMock.updateProducts(eq(cartId), any())).thenReturn(response);

    mvc.perform(
            MockMvcRequestBuilders.put("/cart/{id}/products", cartId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(request)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cartId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()").value(products.size()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(1000.0));
  }

  @ParameterizedTest
  @DisplayName("WHEN POST /cart with invalid CreateCartRequest THEN response BadRequest")
  @MethodSource("invalidCartRequests")
  void createInvalidCartTest(CreateCartRequest request) throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.post("/cart")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(request)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  static Stream<CreateCartRequest> invalidCartRequests() {
    return Stream.of(
        CreateCartRequestMother.builder()
            .withProducts(new CartProduct("1111", "guitar", -100.0, 1))
            .build(),
        CreateCartRequestMother.builder()
            .withProducts(new CartProduct("", "guitar", 100.0, 1))
            .build(),
        CreateCartRequestMother.builder()
            .withProducts(new CartProduct("1111", "", 100.0, 1))
            .build(),
        CreateCartRequestMother.builder().withProducts(Collections.emptyList()).build(),
        CreateCartRequestMother.builder().withUser(new CartUser("1111", "juan.com")).build(),
        CreateCartRequestMother.builder().withUser(new CartUser("", "xxx@company.com")).build());
  }

  @ParameterizedTest
  @DisplayName(
      "WHEN PUT /cart/{id}/products with invalid ProductModel list THEN response BadRequest")
  @MethodSource("invalidProductRequests")
  void updateCartProductsInvalidTest(UpdateProductsRequest request) throws Exception {
    var cartId = "1111";

    mvc.perform(
            MockMvcRequestBuilders.put("/cart/{id}/products", cartId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(request)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  static Stream<UpdateProductsRequest> invalidProductRequests() {
    return Stream.of(
        new UpdateProductsRequest(List.of(new CartProduct("", "guitar", 100.0, 1))), // SKU vacío
        new UpdateProductsRequest(List.of(new CartProduct("1111", "", 100.0, 1))), // Nombre vacío
        new UpdateProductsRequest(
            List.of(new CartProduct("1111", "guitar", -100.0, 1))), // Precio negativo
        new UpdateProductsRequest(
            List.of(new CartProduct("1111", "guitar", 100.0, 0))) // Cantidad no positiva
        );
  }
}
