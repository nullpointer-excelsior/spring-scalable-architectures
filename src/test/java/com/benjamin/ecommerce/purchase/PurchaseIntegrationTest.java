package com.benjamin.ecommerce.purchase;

import com.benjamin.ecommerce.CheckoutApplication;
import com.benjamin.ecommerce.order.repositories.OrderRepository;
import com.benjamin.ecommerce.payment.repositories.PaymentRepository;
import com.benjamin.ecommerce.purchase.repositories.PurchaseRepository;
import com.benjamin.ecommerce.purchase.repositories.PurchaseRequestRepository;
import com.benjamin.ecommerce.shared.TestUtils;
import com.benjamin.ecommerce.shared.core.order.dto.CreateOrderRequest;
import com.benjamin.ecommerce.shared.core.order.models.OrderProduct;
import com.benjamin.ecommerce.shared.core.payment.dto.CreatePaymentRequest;
import com.benjamin.ecommerce.shared.core.payment.models.PaymentMethod;
import com.benjamin.ecommerce.shared.core.purchase.dto.CreatePurchaseRequest;
import com.benjamin.ecommerce.shared.core.purchase.dto.PurchaseCreatedResponse;
import com.benjamin.ecommerce.shared.core.shipping.dto.CreateShippingRequest;
import com.benjamin.ecommerce.shared.core.shipping.models.DeliveryOption;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CheckoutApplication.class)
@AutoConfigureMockMvc
public class PurchaseIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    PurchaseRequestRepository purchaseRequestRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    void tearDown() {
        purchaseRequestRepository.deleteAll();
        paymentRepository.deleteAll();
        purchaseRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("GIVEN CreatePurchaseRequest WHEN POST /purchases/process THEN save Purchase and PurchaseRequest")
    public void createPurchaseProcessAndSavePurchaseRequestEntityTest() throws Exception {

        var orderProducts = List.of(OrderProduct.builder()
                .sku("1")
                .name("p1")
                .price(1000.0)
                .quantity(2)
                .build());
        var amount = 2000.0;
        var payment = CreatePaymentRequest.builder()
                .method(PaymentMethod.CREDIT_CARD)
                .amount(amount)
                .details(Map.of("cardName", "visa", "cardNumber", "7777"))
                .build();
        var shipping = CreateShippingRequest.builder()
                .address("evergreen 777")
                .city("springfield")
                .fullname("homer simpson")
                .option(DeliveryOption.EXPRESS)
                .build();
        var order = new CreateOrderRequest(orderProducts, amount);
        var purchase = CreatePurchaseRequest.builder()
                .order(order)
                .payment(payment)
                .shipping(shipping)
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/purchases/process")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(purchase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseRequestId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseId").exists())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        PurchaseCreatedResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PurchaseCreatedResponse.class);
        var purchaseRequestEntity = purchaseRequestRepository.findById(response.purchaseRequestId());

        assertThat(purchaseRequestEntity).isNotEmpty();
        assertThat(purchaseRequestEntity.get().getOrderRequest().get("products")).isNotNull();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> productsResult = (List<Map<String, Object>>) purchaseRequestEntity.get().getOrderRequest().get("products");
        assertThat(productsResult).isNotEmpty();
        assertThat(productsResult).hasSize(orderProducts.size());
        for (int i = 0; i < orderProducts.size(); i++) {
            Map<String, Object> productResult = productsResult.get(i);
            OrderProduct expectedProduct = orderProducts.get(i);
            assertThat(productResult).containsEntry("sku", expectedProduct.sku());
            assertThat(productResult).containsEntry("name", expectedProduct.name());
            assertThat(productResult).containsEntry("price", expectedProduct.price());
            assertThat(productResult).containsEntry("quantity", expectedProduct.quantity());
        }
        assertThat(purchaseRequestEntity.get().getOrderRequest().get("products")).isNotNull();
        assertThat(purchaseRequestEntity.get().getOrderRequest().get("amount")).isEqualTo(order.amount());

        assertThat(purchaseRequestEntity.get().getPaymentRequest().get("method")).isEqualTo(payment.method().toString());
        assertThat(purchaseRequestEntity.get().getPaymentRequest().get("amount")).isEqualTo(payment.amount());
        assertThat(purchaseRequestEntity.get().getPaymentRequest().get("details")).isNotNull();
        @SuppressWarnings("unchecked")
        Map<String, String> detailsResult = (Map<String, String>) purchaseRequestEntity.get().getPaymentRequest().get("details");
        assertThat(detailsResult).containsEntry("cardName", payment.details().get("cardName"));
        assertThat(detailsResult).containsEntry("cardNumber", payment.details().get("cardNumber"));

        assertThat(purchaseRequestEntity.get().getShippingRequest().get("address")).isEqualTo(shipping.address());
        assertThat(purchaseRequestEntity.get().getShippingRequest().get("city")).isEqualTo(shipping.city());
        assertThat(purchaseRequestEntity.get().getShippingRequest().get("fullname")).isEqualTo(shipping.fullname());
        assertThat(purchaseRequestEntity.get().getShippingRequest().get("option")).isEqualTo(shipping.option().toString());
    }

    @Test
    @DisplayName("GIVEN CreatePurchaseRequest WHEN POST /purchases/process THEN create Payment AND create Order")
    public void createPurchaseProcessAndCreatePaymentTest() throws Exception {

        var orderProducts = List.of(OrderProduct.builder()
                .sku("1")
                .name("p1")
                .price(1000.0)
                .quantity(2)
                .build());
        var amount = 2000.0;
        var payment = CreatePaymentRequest.builder()
                .method(PaymentMethod.CREDIT_CARD)
                .amount(amount)
                .details(Map.of("cardName", "visa", "cardNumber", "7777"))
                .build();
        var shipping = CreateShippingRequest.builder()
                .address("evergreen 777")
                .city("springfield")
                .fullname("homer simpson")
                .option(DeliveryOption.EXPRESS)
                .build();
        var order = new CreateOrderRequest(orderProducts, amount);
        var purchase = CreatePurchaseRequest.builder()
                .order(order)
                .payment(payment)
                .shipping(shipping)
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/purchases/process")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(purchase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseRequestId").exists())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        PurchaseCreatedResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PurchaseCreatedResponse.class);
        var purchaseRequestEntity = purchaseRequestRepository.findById(response.purchaseRequestId());

        assertThat(paymentRepository.count()).isEqualTo(1L);
        assertThat(orderRepository.count()).isEqualTo(1L);

    }
}
