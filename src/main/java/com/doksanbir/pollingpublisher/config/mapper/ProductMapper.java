package com.doksanbir.pollingpublisher.config.mapper;

import com.doksanbir.pollingpublisher.model.Product;
import com.doksanbir.pollingpublisher.model.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}