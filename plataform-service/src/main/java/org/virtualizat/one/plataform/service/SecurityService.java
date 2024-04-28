package org.virtualizat.one.plataform.service;

import org.virtualizat.one.plataform.entity.User;
import org.virtualizat.one.plataform.entity.enums.State;

import java.util.List;
import java.util.UUID;

public interface SecurityService {
    List<User> getAllUser();
    public User getUser(UUID id);
    public User createUser(User user);
    public User updateUser(User user);
    public User deleteUser(UUID id);

    public List<User> findByState(State state);
}
