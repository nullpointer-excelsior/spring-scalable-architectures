package com.benjamin.ecommerce.payment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.benjamin.ecommerce.payment.restcontrollers.PaymentMethodRestController;
import com.benjamin.ecommerce.shared.TestUtils;
import com.benjamin.ecommerce.shared.core.payment.PaymentUseCases;
import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodRequest;
import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodResponse;
import com.benjamin.ecommerce.shared.core.payment.models.PaymentMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

@WebMvcTest(PaymentMethodRestController.class)
public class PaymentMethodRestControllerTest {

  @Autowired MockMvc mvc;
  @MockitoBean
  PaymentUseCases paymentUseCasesMock;

  @Test
  @DisplayName("WHEN request POST /payment-methods/validate with ValidatePaymentMethodRequest THEN response with ValidatePaymentMethodResponse OK")
  public void validatePaymentOK() throws Exception {
    var body =
        new ValidatePaymentMethodRequest(PaymentMethod.CREDIT_CARD, Map.of("Number", "22222222"));
    when(paymentUseCasesMock.validatePaymentMethod(any()))
        .thenReturn(new ValidatePaymentMethodResponse("CREDIT_CARD", true));

    mvc.perform(
            MockMvcRequestBuilders.post("/payment-methods/validate")
                .content(TestUtils.asJsonString(body))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.method").value("CREDIT_CARD"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.valid").value("true"));
  }

  @Test
  @DisplayName("WHEN request POST /payment-methods/validate with invalid ValidatePaymentMethodRequest THEN response 409")
  public void validatePaymentMethodNullTest() throws Exception {
    var body = Map.of("details", Map.of("key", "value"));
    when(paymentUseCasesMock.validatePaymentMethod(any()))
            .thenReturn(new ValidatePaymentMethodResponse("CREDIT_CARD", true));

    mvc.perform(
                    MockMvcRequestBuilders.post("/payment-methods/validate")
                            .content(TestUtils.asJsonString(body))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.method").doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$.valid").doesNotExist());
  }
}
