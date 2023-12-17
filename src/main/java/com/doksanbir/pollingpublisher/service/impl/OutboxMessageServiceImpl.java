package com.doksanbir.pollingpublisher.service.impl;

import com.doksanbir.pollingpublisher.model.OutboxMessage;
import com.doksanbir.pollingpublisher.repository.OutboxMessageRepository;
import com.doksanbir.pollingpublisher.service.OutboxMessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxMessageServiceImpl implements OutboxMessageService {

    private final OutboxMessageRepository outboxMessageRepository;

    @Override
    @Transactional
    public OutboxMessage saveOrUpdate(OutboxMessage outboxMessage) {
        log.info("Saving or updating outbox message: {}", outboxMessage);
        return outboxMessageRepository.save(outboxMessage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutboxMessage> findByStatus(OutboxMessage.MessageStatus status) {
        return outboxMessageRepository.findByStatus(status);
    }

    @Override
    public Optional<OutboxMessage> findById(Long id) {
        log.info("Finding outbox message by ID: {}", id);
        return outboxMessageRepository.findById(id);
    }

    @Override
    public List<OutboxMessage> findAll() {
        log.info("Finding all outbox messages");
        return outboxMessageRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting outbox message by ID: {}", id);
        if (!outboxMessageRepository.existsById(id)) {
            log.error("Outbox message not found with ID: {}", id);
            throw new EntityNotFoundException("Outbox message not found with ID: " + id);
        }
        outboxMessageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(OutboxMessage outboxMessage) {
        log.info("Deleting outbox message: {}", outboxMessage);
        outboxMessageRepository.delete(outboxMessage);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<OutboxMessage> outboxMessages) {
        log.info("Deleting multiple outbox messages");
        outboxMessageRepository.deleteAll(outboxMessages);
    }

    @Override
    @Transactional
    public List<OutboxMessage> saveAll(Iterable<OutboxMessage> outboxMessages) {
        log.info("Saving multiple outbox messages");
        return outboxMessageRepository.saveAll(outboxMessages);
    }
}
