package com.benjamin.ecommerce.order.services;

import com.benjamin.ecommerce.order.mappers.OrderMapper;
import com.benjamin.ecommerce.order.repositories.OrderRepository;
import com.benjamin.ecommerce.order.OrderUseCases;
import com.benjamin.ecommerce.order.dto.models.Order;
import com.benjamin.ecommerce.order.dto.models.OrderStatus;
import com.benjamin.ecommerce.purchase.dto.CreateOrder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class OrderService implements OrderUseCases {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order create(CreateOrder request) {
        var order = orderMapper.toEntity(request, OrderStatus.CREATED);
        order.getProducts().forEach(product -> product.setOrder(order));
        orderRepository.save(order);
        return orderMapper.toModel(order);
    }
}
