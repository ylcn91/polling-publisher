package com.doksanbir.pollingpublisher.config.mapper;


import com.doksanbir.pollingpublisher.model.Orders;
import com.doksanbir.pollingpublisher.model.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Orders order);

    Orders toEntity(OrderDto orderDto);
}