package com.doksanbir.pollingpublisher.service.impl;

import com.doksanbir.pollingpublisher.model.Orders;
import com.doksanbir.pollingpublisher.repository.OrderRepository;
import com.doksanbir.pollingpublisher.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public List<Orders> saveAll(Iterable<Orders> orders) {
        return orderRepository.saveAll(orders);
    }

    @Override
    public Orders saveOrUpdate(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Orders> findById(Long id) {
        Objects.requireNonNull(id, "Id cannot be null");
        return orderRepository.findById(id);
    }
    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            orderRepository.deleteById(id);
            log.info("Deleted order with ID {}", id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Order not found with ID: {}", id);
            throw new EntityNotFoundException("Order not found with ID: " + id);
        }
    }

    @Override
    @Transactional
    public void delete(Orders order) {
        orderRepository.delete(order);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Orders> order) {
        orderRepository.deleteAll(order);
    }
}

