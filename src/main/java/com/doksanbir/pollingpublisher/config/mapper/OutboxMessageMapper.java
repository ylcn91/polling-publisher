package com.doksanbir.pollingpublisher.config.mapper;

import com.doksanbir.pollingpublisher.model.OutboxMessage;
import com.doksanbir.pollingpublisher.model.dto.OutboxMessageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutboxMessageMapper {
    OutboxMessageDto toDto(OutboxMessage outboxMessage);

    OutboxMessage toEntity(OutboxMessageDto outboxMessageDto);
}
