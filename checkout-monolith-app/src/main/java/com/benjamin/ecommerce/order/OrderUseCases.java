package com.benjamin.ecommerce.order;

import com.benjamin.ecommerce.order.models.Order;
import com.benjamin.ecommerce.purchase.dto.CreateOrder;

public interface OrderUseCases {

    Order create(CreateOrder request);

}