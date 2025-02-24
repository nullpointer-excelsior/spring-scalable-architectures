package com.benjamin.ecommerce.purchase.restcontrollers;

import com.benjamin.ecommerce.shared.core.purchase.PurchaseProcessCoordinator;
import com.benjamin.ecommerce.shared.core.purchase.dto.CreatePurchaseRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseRestController {

    @Autowired
    private PurchaseProcessCoordinator purchaseProcessCoordinator;

    @PostMapping("/process")
    public void process(@Valid @RequestBody CreatePurchaseRequest request) {
        this.purchaseProcessCoordinator.process(request);
    }
}
