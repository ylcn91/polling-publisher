package com.doksanbir.pollingpublisher.service;

import com.doksanbir.pollingpublisher.model.OutboxMessage;
import java.util.List;

public interface OutboxMessageService extends BaseService<OutboxMessage, Long> {
    List<OutboxMessage> findByStatus(OutboxMessage.MessageStatus status);
}
