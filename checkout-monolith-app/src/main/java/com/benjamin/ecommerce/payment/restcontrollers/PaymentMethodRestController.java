package com.benjamin.ecommerce.payment.restcontrollers;

import com.benjamin.ecommerce.payment.PaymentUseCases;
import com.benjamin.ecommerce.payment.dto.ValidatePaymentMethodRequest;
import com.benjamin.ecommerce.payment.dto.ValidatePaymentMethodResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodRestController {

    @Autowired
    private PaymentUseCases paymentUseCases;

    @PostMapping("validate")
    public ValidatePaymentMethodResponse validate(@RequestBody @Valid ValidatePaymentMethodRequest request) {
        return this.paymentUseCases.validatePaymentMethod(request);
    }
}
