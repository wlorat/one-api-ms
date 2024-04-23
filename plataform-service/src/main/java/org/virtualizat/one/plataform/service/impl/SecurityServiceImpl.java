package org.virtualizat.one.plataform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.virtualizat.one.plataform.entity.Role;
import org.virtualizat.one.plataform.entity.User;
import org.virtualizat.one.plataform.entity.enums.Rol;
import org.virtualizat.one.plataform.entity.enums.State;
import org.virtualizat.one.plataform.repository.RoleRepository;
import org.virtualizat.one.plataform.service.SecurityService;
import org.virtualizat.one.plataform.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public User getUser(UUID id) {

        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User no encontrado con id " + id));
    }

    @Override
    public User createUser(User newUser) {
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Usario ya existe .");
        }
        String encryptedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);

        for (Role role : newUser.getRoles()) {
            Role auxRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Role with ID " + role.getId() + " not found"));
            newUser.getRoles().add(auxRole);
        }
        // Obtener el rol USER
        Role userRole = roleRepository.findByRol(Rol.USER)
                .orElseThrow(() -> new IllegalArgumentException("Se debe crear el rol ROOT"));

        newUser.getRoles().add(userRole);
        newUser.setState(State.CREATED);
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User user) {
        User userDB = getUser(user.getId());
        if (null == userDB) {
            return null;
        }
        userDB.setUsername(user.getUsername());
        userDB.setHashtag(user.getHashtag());
        return userRepository.save(userDB);
    }

    @Override
    public User deleteUser(User user) {
        User userDB = getUser(user.getId());
        if (null == userDB) {
            return null;
        }
        userDB.setState(State.DELETED);
        return userRepository.save(userDB);
    }

    @Override
    public List<User> findByState(State state) {
        return userRepository.findByState(state);
    }


}
