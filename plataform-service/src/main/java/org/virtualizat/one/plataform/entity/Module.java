package org.virtualizat.one.plataform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.virtualizat.one.plataform.entity.enums.Category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="module", schema="app")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(length = 36)
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="version")
    private String version;
    @Column(name="description")
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Option> options;
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "permission_role",
            schema = "app",
            joinColumns = @JoinColumn(name = "module"),
            inverseJoinColumns = @JoinColumn(name = "role")
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return id != null && id.equals(module.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
