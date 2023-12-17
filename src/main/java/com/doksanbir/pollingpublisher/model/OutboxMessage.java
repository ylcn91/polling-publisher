package com.doksanbir.pollingpublisher.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OutboxMessage extends BaseEntity {

    private String topic;
    private String payload;
    private MessageStatus status;
    private EventType eventType;

    public enum EventType {
        CREATE, UPDATE, DELETE
    }

    public enum MessageStatus {
        UNSENT, SENT
    }
}
