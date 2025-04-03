package com.benjamin.ecommerce.payment;

import com.benjamin.ecommerce.payment.dto.ValidatePaymentMethodRequest;
import com.benjamin.ecommerce.payment.dto.ValidatePaymentMethodResponse;
import com.benjamin.ecommerce.payment.models.Payment;
import com.benjamin.ecommerce.purchase.dto.CreatePayment;

public interface PaymentUseCases {
    ValidatePaymentMethodResponse validatePaymentMethod(ValidatePaymentMethodRequest request);
    Payment create(CreatePayment request);
}
