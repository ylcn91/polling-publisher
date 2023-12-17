package com.doksanbir.pollingpublisher.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        try {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            log.info("Creating ProducerFactory with config: {}", configProps);
            return new DefaultKafkaProducerFactory<>(configProps);
        } catch (Exception e) {
            log.error("Error creating ProducerFactory", e);
            throw new RuntimeException("Error creating ProducerFactory", e);
        }
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        log.info("Creating KafkaTemplate");
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        log.info("Creating ConsumerFactory with config: {}", configProps);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public NewTopic userActivityTopic() {
        return new NewTopic("userActivity", 1, (short) 1);
    }

    @Bean
    public NewTopic productUpdateTopic() {
        return new NewTopic("productUpdate", 1, (short) 1);
    }

    @Bean
    public NewTopic orderProcessingTopic() {
        return new NewTopic("orderProcessing", 1, (short) 1);
    }

    @Bean
    public NewTopic inventoryManagementTopic() {
        return new NewTopic("inventoryManagement", 1, (short) 1);
    }

    @Bean
    public NewTopic outboxEventTopic() {
        return new NewTopic("outboxEvents", 1, (short) 1);
    }


}

