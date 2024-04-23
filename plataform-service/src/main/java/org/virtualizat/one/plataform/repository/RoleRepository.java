package org.virtualizat.one.plataform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virtualizat.one.plataform.entity.Role;
import org.virtualizat.one.plataform.entity.enums.Rol;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRol(Rol rol);
}
