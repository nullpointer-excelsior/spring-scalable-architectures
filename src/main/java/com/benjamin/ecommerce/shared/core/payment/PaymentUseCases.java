package com.benjamin.ecommerce.shared.core.payment;

import com.benjamin.ecommerce.shared.core.payment.dto.CreatePaymentRequest;
import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodRequest;
import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodResponse;
import com.benjamin.ecommerce.shared.core.payment.models.Payment;

public interface PaymentUseCases {
    ValidatePaymentMethodResponse validatePaymentMethod(ValidatePaymentMethodRequest request);
    Payment create(CreatePaymentRequest request);
}
