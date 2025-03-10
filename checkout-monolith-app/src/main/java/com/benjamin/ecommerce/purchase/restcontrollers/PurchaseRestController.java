package com.benjamin.ecommerce.purchase.restcontrollers;

import com.benjamin.ecommerce.purchase.PurchaseProcessCoordinator;
import com.benjamin.ecommerce.purchase.dto.CreatePurchaseRequest;
import com.benjamin.ecommerce.purchase.dto.PurchaseCreatedResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@RequestMapping("/purchases")
public class PurchaseRestController {

    @Autowired
    private PurchaseProcessCoordinator purchaseProcessCoordinator;

    @PostMapping("/process")
    public PurchaseCreatedResponse process(@Valid @RequestBody CreatePurchaseRequest request) {
        return this.purchaseProcessCoordinator.process(request);
    }
}
