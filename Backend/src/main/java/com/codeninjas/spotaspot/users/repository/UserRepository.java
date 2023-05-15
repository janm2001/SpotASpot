package com.codeninjas.spotaspot.users.repository;

import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.users.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
