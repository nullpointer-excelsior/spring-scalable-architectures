package com.benjamin.ecommerce.purchase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.benjamin.ecommerce.purchase.restcontrollers.PurchaseRestController;
import com.benjamin.ecommerce.shared.TestUtils;
import com.benjamin.ecommerce.shared.core.purchase.PurchaseProcessCoordinator;
import com.benjamin.ecommerce.shared.core.purchase.dto.CreatePurchaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(PurchaseRestController.class)
public class PurchaseRestControllerTest {

  @Autowired MockMvc mvc;
  @MockitoBean
  PurchaseProcessCoordinator purchaseProcessCoordinatorMock;

  @Test
  @DisplayName("WHEN POST /purchase/process with CreatePurchaseRequest valid THEN response OK")
  public void createPurchaseProcessTest() throws Exception {

    var body = CreatePurchaseRequest.builder().build();

    doNothing().when(purchaseProcessCoordinatorMock).process((CreatePurchaseRequest) any());

    mvc.perform(
            MockMvcRequestBuilders.post("/purchase/process")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(body)))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
