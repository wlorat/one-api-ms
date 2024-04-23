package org.virtualizat.one.plataform.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virtualizat.one.plataform.entity.User;
import org.virtualizat.one.plataform.entity.enums.State;

public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByUsername(String username);
    List<User> findByState(State state);
}
