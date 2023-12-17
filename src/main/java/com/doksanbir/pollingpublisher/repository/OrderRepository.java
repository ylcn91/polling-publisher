package com.doksanbir.pollingpublisher.repository;

import com.doksanbir.pollingpublisher.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
