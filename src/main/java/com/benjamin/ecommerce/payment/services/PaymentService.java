package com.benjamin.ecommerce.payment.services;

import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodRequest;
import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public ValidatePaymentMethodResponse validatePaymentMethod(ValidatePaymentMethodRequest request) {
        throw new UnsupportedOperationException("not implemented");
    }
}
