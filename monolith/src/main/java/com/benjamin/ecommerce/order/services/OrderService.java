package com.benjamin.ecommerce.order.services;

import com.benjamin.ecommerce.order.mappers.OrderMapper;
import com.benjamin.ecommerce.order.repositories.OrderRepository;
import com.benjamin.ecommerce.order.OrderUseCases;
import com.benjamin.ecommerce.order.models.Order;
import com.benjamin.ecommerce.order.models.OrderStatus;
import com.benjamin.ecommerce.purchase.dto.CreateOrder;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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
