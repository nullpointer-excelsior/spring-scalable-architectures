package com.benjamin.ecommerce.shared.core.payment;

import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodRequest;
import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodResponse;
import com.benjamin.ecommerce.shared.core.payment.models.Payment;
import com.benjamin.ecommerce.shared.core.purchase.dto.CreatePayment;

public interface PaymentUseCases {
    ValidatePaymentMethodResponse validatePaymentMethod(ValidatePaymentMethodRequest request);
    Payment create(CreatePayment request);
}
