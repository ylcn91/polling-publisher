package com.doksanbir.pollingpublisher.config.mapper;

import com.doksanbir.pollingpublisher.model.Location;
import com.doksanbir.pollingpublisher.model.dto.LocationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto toDto(Location location);

    Location toEntity(LocationDto locationDto);
}
