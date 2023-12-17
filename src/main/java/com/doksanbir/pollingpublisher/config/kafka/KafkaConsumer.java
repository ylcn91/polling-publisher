package com.doksanbir.pollingpublisher.config.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = "", groupId = "")
    public void listen(String message) {
        log.info("Received message: {}", message);
        try {
           log.info("Processing message: {}", message);
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
            // Handle the processing exception
        }
    }
}
