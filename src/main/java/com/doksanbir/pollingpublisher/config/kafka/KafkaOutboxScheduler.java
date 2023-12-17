package com.doksanbir.pollingpublisher.config.kafka;

import com.doksanbir.pollingpublisher.model.OutboxMessage;
import com.doksanbir.pollingpublisher.service.OutboxMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaOutboxScheduler {

    private final OutboxMessageService outboxMessageService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 10000)
    public void publishUnsentMessages() {
        List<OutboxMessage> unsentMessages = outboxMessageService.findByStatus(OutboxMessage.MessageStatus.UNSENT);
        for (OutboxMessage message : unsentMessages) {
            kafkaTemplate.send(message.getTopic(), message.getPayload());
            message.setStatus(OutboxMessage.MessageStatus.SENT);
            outboxMessageService.saveOrUpdate(message);
        }
    }
}
