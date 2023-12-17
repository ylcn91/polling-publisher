package com.doksanbir.pollingpublisher.repository;

import com.doksanbir.pollingpublisher.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
