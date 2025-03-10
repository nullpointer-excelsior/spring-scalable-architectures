package com.benjamin.ecommerce.order.mappers;

import com.benjamin.ecommerce.order.entities.OrderEntity;
import com.benjamin.ecommerce.order.models.Order;
import com.benjamin.ecommerce.order.models.OrderStatus;
import com.benjamin.ecommerce.purchase.dto.CreateOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderProductMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(CreateOrder order, OrderStatus status);

    Order toModel(OrderEntity entity);

}
