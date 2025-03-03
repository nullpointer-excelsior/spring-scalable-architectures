package com.benjamin.ecommerce.shipping;

import com.benjamin.ecommerce.purchase.dto.CreateShipping;
import com.benjamin.ecommerce.shipping.models.Shipping;

public interface ShippingUseCases {

    Shipping create(CreateShipping request);
}
