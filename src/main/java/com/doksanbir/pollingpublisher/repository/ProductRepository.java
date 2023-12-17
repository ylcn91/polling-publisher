package com.doksanbir.pollingpublisher.repository;

import com.doksanbir.pollingpublisher.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
