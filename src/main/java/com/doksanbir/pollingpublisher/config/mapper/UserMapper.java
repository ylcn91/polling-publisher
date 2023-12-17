package com.doksanbir.pollingpublisher.config.mapper;

import com.doksanbir.pollingpublisher.model.User;
import com.doksanbir.pollingpublisher.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
