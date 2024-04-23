package org.virtualizat.one.plataform.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="option", schema="app")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(length = 36)
    private UUID id;
    @Column(name="option")
    private String option;
    @Column(name="description")
    private String description;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "module")
    private Module module;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id != null && id.equals(option.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
