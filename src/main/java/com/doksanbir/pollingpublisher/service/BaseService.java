package com.doksanbir.pollingpublisher.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    T saveOrUpdate(T entity); // Combined save and update

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    void delete(T entity);

    List<T> saveAll(Iterable<T> entities); // Batch save

    void deleteAll(Iterable<T> entities); // Batch delete
}
