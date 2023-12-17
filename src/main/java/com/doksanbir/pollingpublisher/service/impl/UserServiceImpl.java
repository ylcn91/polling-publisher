package com.doksanbir.pollingpublisher.service.impl;

import com.doksanbir.pollingpublisher.model.User;
import com.doksanbir.pollingpublisher.repository.UserRepository;
import com.doksanbir.pollingpublisher.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<User> users) {
        userRepository.deleteAll(users);
    }

    @Override
    @Transactional
    public List<User> saveAll(Iterable<User> users) {
        return userRepository.saveAll(users);
    }
}
