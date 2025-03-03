package com.benjamin.ecommerce.purchase;

import com.benjamin.ecommerce.CheckoutApplication;
import com.benjamin.ecommerce.order.repositories.OrderRepository;
import com.benjamin.ecommerce.payment.repositories.PaymentRepository;
import com.benjamin.ecommerce.products.entities.ProductEntity;
import com.benjamin.ecommerce.products.repositories.ProductRepository;
import com.benjamin.ecommerce.purchase.entities.PurchaseEntity;
import com.benjamin.ecommerce.purchase.entities.PurchaseRequestEntity;
import com.benjamin.ecommerce.purchase.models.PurchaseStatus;
import com.benjamin.ecommerce.purchase.repositories.PurchaseRepository;
import com.benjamin.ecommerce.purchase.repositories.PurchaseRequestRepository;
import com.benjamin.ecommerce.shared.TestUtils;
import com.benjamin.ecommerce.order.dto.CreateOrderRequest;
import com.benjamin.ecommerce.order.models.OrderProduct;
import com.benjamin.ecommerce.payment.dto.CreatePaymentRequest;
import com.benjamin.ecommerce.payment.models.PaymentMethod;
import com.benjamin.ecommerce.purchase.dto.CreatePurchaseRequest;
import com.benjamin.ecommerce.purchase.dto.PurchaseCreatedResponse;
import com.benjamin.ecommerce.shipping.dto.CreateShippingRequest;
import com.benjamin.ecommerce.shipping.models.DeliveryOption;
import com.benjamin.ecommerce.shipping.repositories.ShippingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
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

    @Autowired
    ShippingRepository shippingRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void tearDown() {
        purchaseRequestRepository.deleteAll();
        paymentRepository.deleteAll();
        purchaseRepository.deleteAll();
        orderRepository.deleteAll();
        shippingRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("GIVEN CreatePurchaseRequest WHEN POST /purchases/process THEN save PurchaseRequest")
    public void createPurchaseProcessAndSavePurchaseRequestEntityTest() throws Exception {

        var purchase = getCreatePurchaseRequest();
        var order = purchase.order();
        var orderProducts = purchase.order().products();
        var payment = purchase.payment();
        var shipping = purchase.shipping();

        var result = mvc.perform(MockMvcRequestBuilders.post("/purchases/process")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(purchase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseRequestId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseId").exists())
                .andReturn();

        PurchaseCreatedResponse response = getPurchaseCreatedResponse(result);
        var purchaseRequestEntity = purchaseRequestRepository.findById(response.purchaseRequestId());

        assertThat(purchaseRequestEntity).isNotEmpty();
        assertThat(purchaseRequestEntity.get().getOrderRequest().get("products"))
                .isNotNull();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> productsResult = (List<Map<String, Object>>)
                purchaseRequestEntity.get().getOrderRequest().get("products");
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
        assertThat(purchaseRequestEntity.get().getOrderRequest().get("products"))
                .isNotNull();
        assertThat(purchaseRequestEntity.get().getOrderRequest().get("amount")).isEqualTo(order.amount());

        assertThat(purchaseRequestEntity.get().getPaymentRequest().get("method"))
                .isEqualTo(payment.method().toString());
        assertThat(purchaseRequestEntity.get().getPaymentRequest().get("amount"))
                .isEqualTo(payment.amount());
        assertThat(purchaseRequestEntity.get().getPaymentRequest().get("details"))
                .isNotNull();
        @SuppressWarnings("unchecked")
        Map<String, String> detailsResult = (Map<String, String>)
                purchaseRequestEntity.get().getPaymentRequest().get("details");
        assertThat(detailsResult).containsEntry("cardName", payment.details().get("cardName"));
        assertThat(detailsResult).containsEntry("cardNumber", payment.details().get("cardNumber"));

        assertThat(purchaseRequestEntity.get().getShippingRequest().get("address"))
                .isEqualTo(shipping.address());
        assertThat(purchaseRequestEntity.get().getShippingRequest().get("city")).isEqualTo(shipping.city());
        assertThat(purchaseRequestEntity.get().getShippingRequest().get("fullname"))
                .isEqualTo(shipping.fullname());
        assertThat(purchaseRequestEntity.get().getShippingRequest().get("option"))
                .isEqualTo(shipping.option().toString());
    }

    @Test
    @DisplayName("GIVEN CreatePurchaseRequest WHEN POST /purchases/process THEN save Purchase")
    public void createPurchaseProcessAndSavePurchaseEntityTest() throws Exception {

        var purchase = getCreatePurchaseRequest();

        var result = mvc.perform(MockMvcRequestBuilders.post("/purchases/process")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(purchase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseRequestId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseId").exists())
                .andReturn();

        PurchaseCreatedResponse response = getPurchaseCreatedResponse(result);
        var purchaseEntity = purchaseRepository.findById(response.purchaseId());

        assertThat(purchaseEntity).isNotEmpty().get()
                .extracting(PurchaseEntity::getId, PurchaseEntity::getPurchaseRequest)
                .isNotNull();


    }

    private static PurchaseCreatedResponse getPurchaseCreatedResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result.getResponse().getContentAsString(), PurchaseCreatedResponse.class);
    }

    @Test
    @DisplayName(
            "GIVEN purchase created WHEN POST /purchases/process THEN create Payment AND create Order AND create shipping")
    public void createPurchaseProcessAndCreatePaymentOrderAndShippingTest() throws Exception {

        var purchase = getCreatePurchaseRequest();

        mvc.perform(MockMvcRequestBuilders.post("/purchases/process")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(purchase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseRequestId").exists())
                .andReturn();

        assertThat(paymentRepository.count()).isEqualTo(1L);
        assertThat(orderRepository.count()).isEqualTo(1L);
        assertThat(shippingRepository.count()).isEqualTo(1L);
    }

    @Test
    @DisplayName("GIVEN purchase created WHEN purchase successfully THEN purchase status is SHIPPED")
    public void createPurchaseProcessSuccessfullyTest() throws Exception {

        var purchase = getCreatePurchaseRequest();

        var result = mvc.perform(MockMvcRequestBuilders.post("/purchases/process")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(purchase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseRequestId").exists())
                .andReturn();
        PurchaseCreatedResponse response = getPurchaseCreatedResponse(result);
        var purchaseEntity = purchaseRepository.findById(response.purchaseId());
        assertThat(purchaseEntity).get().extracting(PurchaseEntity::getStatus).isEqualTo(PurchaseStatus.SHIPPED);
    }

    @Transactional
    @Test
    @DisplayName("GIVEN purchase created WHEN POST /purchases/process THEN update product quantity")
    public void createPurchaseProcessAndUpdateProductQuantityTest() throws Exception {

        var product1 = ProductEntity.builder()
                .sku("1")
                .quantity(10)
                .price(1000.0)
                .name("p1")
                .build();
        var product2 = ProductEntity.builder()
                .sku("2")
                .quantity(1)
                .price(1000.0)
                .name("p2")
                .build();
        productRepository.save(product1);
        productRepository.save(product2);

        var orderProducts = List.of(
                OrderProduct.builder()
                        .sku(product1.getSku())
                        .name(product1.getName())
                        .price(product1.getPrice())
                        .quantity(1)
                        .build(),
                OrderProduct.builder()
                        .sku(product2.getSku())
                        .name(product2.getName())
                        .price(product2.getPrice())
                        .quantity(1)
                        .build());
        var amount = 2000.0;
        var payment = CreatePaymentRequest.builder()
                .method(PaymentMethod.CREDIT_CARD)
                .amount(amount)
                .details(Map.of("cardName", "visa", "cardNumber", "7777"))
                .build();
        var shipping = CreateShippingRequest.builder()
                .address("742 Evergreen Terrace")
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

        mvc.perform(MockMvcRequestBuilders.post("/purchases/process")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(purchase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseRequestId").exists())
                .andReturn();

        var product1Result = productRepository.findById(product1.getSku());
        assertThat(product1Result).isNotEmpty().get().extracting("quantity").isEqualTo(9);

        var product2Result = productRepository.findById(product2.getSku());
        assertThat(product2Result).isNotEmpty().get().extracting("quantity").isEqualTo(0);
    }

    private static CreatePurchaseRequest getCreatePurchaseRequest() {
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
                .address("742 Evergreen Terrace")
                .city("springfield")
                .fullname("homer simpson")
                .option(DeliveryOption.EXPRESS)
                .build();
        var order = new CreateOrderRequest(orderProducts, amount);
        return CreatePurchaseRequest.builder()
                .order(order)
                .payment(payment)
                .shipping(shipping)
                .build();
    }
}
