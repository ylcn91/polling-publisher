package com.doksanbir.pollingpublisher.service.impl;

import com.doksanbir.pollingpublisher.model.Location;
import com.doksanbir.pollingpublisher.repository.LocationRepository;
import com.doksanbir.pollingpublisher.service.LocationService;
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
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public Location saveOrUpdate(Location location) {
        log.info("Saving or updating location: {}", location);
        return locationRepository.save(location);
    }

    @Override
    public Optional<Location> findById(Long id) {
        log.info("Finding location by ID: {}", id);
        return locationRepository.findById(id);
    }

    @Override
    public List<Location> findAll() {
        log.info("Finding all locations");
        return locationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting location by ID: {}", id);
        if (!locationRepository.existsById(id)) {
            log.error("Location not found with ID: {}", id);
            throw new EntityNotFoundException("Location not found with ID: " + id);
        }
        locationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Location location) {
        log.info("Deleting location: {}", location);
        locationRepository.delete(location);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Location> locations) {
        log.info("Deleting multiple locations");
        locationRepository.deleteAll(locations);
    }

    @Override
    @Transactional
    public List<Location> saveAll(Iterable<Location> locations) {
        log.info("Saving multiple locations");
        return locationRepository.saveAll(locations);
    }
}
