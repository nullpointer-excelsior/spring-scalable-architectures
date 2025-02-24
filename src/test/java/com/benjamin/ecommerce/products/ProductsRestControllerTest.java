package com.benjamin.ecommerce.products;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.benjamin.ecommerce.products.restcontrollers.ProductRestController;
import com.benjamin.ecommerce.shared.core.products.ProductUseCases;
import com.benjamin.ecommerce.shared.objectmother.ProductResponseMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductRestController.class)
public class ProductsRestControllerTest {

  @Autowired MockMvc mvc;
  @MockitoBean
  ProductUseCases productUseCasesMock;

  @Test
  @DisplayName("GIVEN ProductRestController WHEN request GET /products THEN response ProductResponse OK")
  public void getProductsTest() throws Exception {
    var products = List.of(ProductResponseMother.builder().build());
    when(productUseCasesMock.findAll()).thenReturn(products);
    mvc.perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].sku").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].price").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].quantity").exists());
  }
}
