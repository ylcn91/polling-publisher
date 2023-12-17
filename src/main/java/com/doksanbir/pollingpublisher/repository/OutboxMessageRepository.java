package com.doksanbir.pollingpublisher.repository;

import com.doksanbir.pollingpublisher.model.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long> {
    List<OutboxMessage> findByStatus(OutboxMessage.MessageStatus status);
}
