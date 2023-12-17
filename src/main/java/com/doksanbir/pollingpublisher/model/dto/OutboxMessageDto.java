package com.doksanbir.pollingpublisher.model.dto;

import lombok.Data;

@Data
public class OutboxMessageDto {
    private Long id;
    private String topic;
    private String payload;
    private String status;
}
