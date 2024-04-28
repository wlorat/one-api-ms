package org.virtualizat.one.plataform.service;

import org.virtualizat.one.plataform.entity.Module;
import org.virtualizat.one.plataform.entity.Role;

import java.util.List;
import java.util.UUID;

public interface AppService {
    Role createRole(Role role);
    Module createModule(Module module);

    List<Role> getAllRole();
    List<Module> getAllModule();

    Role getRole(UUID id);
    Module getModule(UUID id);

    Role updateRole(Role role);
    Module updateModule(UUID id, Module moduleDetails);

    Role deleteRole(UUID id);
    Module deleteModule(UUID id);
}
