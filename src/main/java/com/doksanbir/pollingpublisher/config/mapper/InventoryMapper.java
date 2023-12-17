package com.doksanbir.pollingpublisher.config.mapper;


import com.doksanbir.pollingpublisher.model.Inventory;
import com.doksanbir.pollingpublisher.model.dto.InventoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDto toDto(Inventory inventory);

    Inventory toEntity(InventoryDto inventoryDto);
}
