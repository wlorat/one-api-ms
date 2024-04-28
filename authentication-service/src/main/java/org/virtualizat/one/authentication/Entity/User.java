package org.virtualizat.one.authentication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.virtualizat.one.authentication.utils.enums.State;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user", schema="app")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(length = 36)
    private UUID id;
    @Column(name="username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "hashtag")
    private String hashtag;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
}
