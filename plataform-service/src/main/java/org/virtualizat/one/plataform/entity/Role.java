package org.virtualizat.one.plataform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.virtualizat.one.plataform.entity.enums.Rol;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="role", schema="app")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(length = 36)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Rol rol;
    @ManyToMany(mappedBy = "roles")
    private Set<Module> modules;
}
