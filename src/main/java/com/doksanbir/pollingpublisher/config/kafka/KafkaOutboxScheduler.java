package com.doksanbir.pollingpublisher.config.kafka;

import com.doksanbir.pollingpublisher.model.OutboxMessage;
import com.doksanbir.pollingpublisher.service.OutboxMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaOutboxScheduler {

    private final OutboxMessageService outboxMessageService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 10000)
    public void publishUnsentMessages() {
        List<OutboxMessage> unsentMessages = outboxMessageService.findByStatus(OutboxMessage.MessageStatus.UNSENT);
        unsentMessages.parallelStream().forEach(message -> {
            log.info("Publishing message with ID {}, topic: {}, payload: {}", message.getId(), message.getTopic(), message.getPayload());
            kafkaTemplate.send(message.getTopic(), message.getPayload());
            message.setStatus(OutboxMessage.MessageStatus.SENT);
            outboxMessageService.saveOrUpdate(message);
        });
    }
}
