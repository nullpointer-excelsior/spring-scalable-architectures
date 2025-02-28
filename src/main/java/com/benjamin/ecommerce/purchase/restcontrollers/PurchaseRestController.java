package com.benjamin.ecommerce.purchase.restcontrollers;

import com.benjamin.ecommerce.shared.core.purchase.PurchaseProcessCoordinator;
import com.benjamin.ecommerce.shared.core.purchase.dto.CreatePurchaseRequest;
import com.benjamin.ecommerce.shared.core.purchase.dto.PurchaseCreatedResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchases")
public class PurchaseRestController {

    @Autowired
    private PurchaseProcessCoordinator purchaseProcessCoordinator;

    @PostMapping("/process")
    public PurchaseCreatedResponse process(@Valid @RequestBody CreatePurchaseRequest request) {
        return this.purchaseProcessCoordinator.process(request);
    }
}
