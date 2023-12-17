package com.doksanbir.pollingpublisher.config.kafka;

import com.doksanbir.pollingpublisher.model.OutboxMessage;
import com.doksanbir.pollingpublisher.service.OutboxMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaOutboxScheduler {

    private final OutboxMessageService outboxMessageService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 10000)
    public void publishUnsentMessages() {
        List<OutboxMessage> unsentMessages = outboxMessageService.findByStatus(OutboxMessage.MessageStatus.UNSENT);
        unsentMessages.forEach(message -> {
            log.info("Publishing message with ID {}, topic: {}, payload: {}", message.getId(), message.getTopic(), message.getPayload());
            CompletableFuture<SendResult<String, String>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return kafkaTemplate.send(message.getTopic(), message.getPayload()).get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });

            future.thenAccept(result -> {
                message.setStatus(OutboxMessage.MessageStatus.SENT);
                outboxMessageService.saveOrUpdate(message);
                log.info("Sent message with ID {} successfully", message.getId());
            }).exceptionally(ex -> {
                log.error("Failed to send message with ID {}: {}", message.getId(), ex.getMessage());
                return null;
            });
        });
    }
}
