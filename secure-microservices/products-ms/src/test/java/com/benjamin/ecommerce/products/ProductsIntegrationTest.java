package com.benjamin.ecommerce.products;

import com.benjamin.ecommerce.CheckoutApplication;
import com.benjamin.ecommerce.products.entities.ProductEntity;
import com.benjamin.ecommerce.products.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CheckoutApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductsIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void tearDown() {
        this.productRepository.deleteAll();
    }

    @Transactional
    @Test
    @DisplayName("GIVEN ProductRestController WHEN request GET /products THEN response ProductResponse OK")
    public void getProductsTest() throws Exception {

        this.productRepository.saveAll(List.of(
                ProductEntity.builder().sku("1").name("p1").brand("b1").price(1000.0).quantity(10).build(),
                ProductEntity.builder().sku("2").name("p2").brand("b1").price(1000.0).quantity(10).build()
        ));

        mvc.perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].sku").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].brand").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].price").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].quantity").exists());
    }
}
