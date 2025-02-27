package com.benjamin.ecommerce.products.mappers;

import com.benjamin.ecommerce.products.entities.ProductEntity;
import com.benjamin.ecommerce.shared.core.products.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toModel(ProductEntity entity);
}
