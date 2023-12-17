package com.doksanbir.pollingpublisher.config.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        CompletableFuture<SendResult<String, String>> future = CompletableFuture.supplyAsync(() -> {
            try {
                return kafkaTemplate.send(topic, message).get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("Failed to send message={} to topic={}", message, topic, e);
                throw new RuntimeException(e);
            }
        });

        try {
            SendResult<String, String> result = future.get(); // Wait for the result
            log.info("Sent message={} to topic={} with offset={}", message, topic, result.getRecordMetadata().offset());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Failed to send message={} to topic={}", message, topic, e);
        }
    }
}
