package com.doksanbir.pollingpublisher.service.impl;

import com.doksanbir.pollingpublisher.model.Product;
import com.doksanbir.pollingpublisher.repository.ProductRepository;
import com.doksanbir.pollingpublisher.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product saveOrUpdate(Product product) {
        log.info("Saving or updating product {}", product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        log.debug("Searching for product with ID {}", id);
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            log.error("Product not found with ID: {}", id);
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
        log.info("Deleting product with ID {}", id);
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        log.debug("Deleting product {}", product);
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Product> products) {
        log.debug("Deleting all products");
        productRepository.deleteAll(products);
    }

    @Override
    @Transactional
    public List<Product> saveAll(Iterable<Product> products) {
        log.debug("Saving multiple products");
        return productRepository.saveAll(products);
    }
}
