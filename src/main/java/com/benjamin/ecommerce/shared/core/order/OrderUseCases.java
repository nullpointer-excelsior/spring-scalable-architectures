package com.benjamin.ecommerce.shared.core.order;

import com.benjamin.ecommerce.shared.core.order.models.Order;
import com.benjamin.ecommerce.shared.core.purchase.dto.CreateOrder;

public interface OrderUseCases {

    Order create(CreateOrder request);

}