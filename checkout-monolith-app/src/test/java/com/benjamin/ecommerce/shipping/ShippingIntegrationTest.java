package com.benjamin.ecommerce.shipping;

import com.benjamin.ecommerce.CheckoutApplication;
import com.benjamin.ecommerce.products.entities.ProductEntity;
import com.benjamin.ecommerce.shared.TestUtils;
import com.benjamin.ecommerce.shipping.entities.DeliveryEntity;
import com.benjamin.ecommerce.shipping.entities.ShippingEntity;
import com.benjamin.ecommerce.shipping.mappers.ShippingMapper;
import com.benjamin.ecommerce.shipping.models.DeliveryOption;
import com.benjamin.ecommerce.shipping.models.ShippingStatus;
import com.benjamin.ecommerce.shipping.repositories.ShippingRepository;
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

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CheckoutApplication.class)
@AutoConfigureMockMvc
public class ShippingIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ShippingRepository shippingRepository;

    @Autowired
    ShippingMapper shippingMapper;

    @BeforeEach
    void tearDown(){
        shippingRepository.deleteAll();
    }

    @Transactional
    @Test
    @DisplayName("GIVEN Shipping created WHEN request PUT /shipping THEN update Shipping")
    public void updateShippingTest() throws Exception {

        var shipping = ShippingEntity.builder()
                .shippedAt(LocalDateTime.now())
                .purchaseId(1L)
                .status(ShippingStatus.SHIPPED)
                .delivery(DeliveryEntity.builder()
                        .address("742 Evergreen Terrace")
                        .city("Springfield")
                        .option(DeliveryOption.EXPRESS)
                        .fullname("Homer Simpson")
                        .build())
                .build();

        shippingRepository.save(shipping);

        shipping.setStatus(ShippingStatus.DELIVERED);
        var request = shippingMapper.toModel(shipping);

        mvc.perform(MockMvcRequestBuilders.put("/shipping")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.delivery").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shippedAt").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(ShippingStatus.DELIVERED.toString()));
    }

}
