package org.virtualizat.one.plataform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virtualizat.one.plataform.entity.Option;

import java.util.UUID;

public interface OptionRepository extends JpaRepository<Option, UUID> {
}
