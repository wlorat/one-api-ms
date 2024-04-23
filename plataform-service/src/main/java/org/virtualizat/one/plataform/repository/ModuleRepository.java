package org.virtualizat.one.plataform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virtualizat.one.plataform.entity.enums.Category;
import org.virtualizat.one.plataform.entity.Module;

import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
    Optional<Module> findByName(String name);
    boolean existsByCategory(Category category);
}
