package com.benjamin.ecommerce.carts.mappers;

import com.benjamin.ecommerce.carts.entities.CartEntity;
import com.benjamin.ecommerce.carts.models.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CartUserMapper.class, CartProductMapper.class})
public interface CartMapper {

    Cart toModel(CartEntity cartEntity);
}
