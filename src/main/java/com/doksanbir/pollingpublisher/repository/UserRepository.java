package com.doksanbir.pollingpublisher.repository;

import com.doksanbir.pollingpublisher.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
