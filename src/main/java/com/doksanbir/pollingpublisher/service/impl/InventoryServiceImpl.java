package com.doksanbir.pollingpublisher.service.impl;

import com.doksanbir.pollingpublisher.model.Inventory;
import com.doksanbir.pollingpublisher.repository.InventoryRepository;
import com.doksanbir.pollingpublisher.service.InventoryService;
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
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public Inventory saveOrUpdate(Inventory inventory) {
        log.info("Saving or updating inventory: {}", inventory);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        log.info("Finding inventory by ID: {}", id);
        return inventoryRepository.findById(id);
    }

    @Override
    public List<Inventory> findAll() {
        log.info("Finding all inventory items");
        return inventoryRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting inventory by ID: {}", id);
        if (!inventoryRepository.existsById(id)) {
            log.error("Inventory not found with ID: {}", id);
            throw new EntityNotFoundException("Inventory not found with ID: " + id);
        }
        inventoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Inventory inventory) {
        log.info("Deleting inventory: {}", inventory);
        inventoryRepository.delete(inventory);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Inventory> inventories) {
        log.info("Deleting multiple inventory items");
        inventoryRepository.deleteAll(inventories);
    }

    @Override
    @Transactional
    public List<Inventory> saveAll(Iterable<Inventory> inventories) {
        log.info("Saving multiple inventory items");
        return inventoryRepository.saveAll(inventories);
    }
}
