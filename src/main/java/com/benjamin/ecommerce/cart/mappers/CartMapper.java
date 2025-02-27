package com.benjamin.ecommerce.cart.mappers;

import com.benjamin.ecommerce.cart.entities.CartEntity;
import com.benjamin.ecommerce.shared.core.cart.models.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CartUserMapper.class, CartProductMapper.class})
public interface CartMapper {

    Cart toModel(CartEntity cartEntity);
}
