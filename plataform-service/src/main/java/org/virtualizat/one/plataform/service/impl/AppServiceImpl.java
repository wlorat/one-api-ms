package org.virtualizat.one.plataform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.virtualizat.one.plataform.entity.Module;
import org.virtualizat.one.plataform.entity.Option;
import org.virtualizat.one.plataform.entity.Role;
import org.virtualizat.one.plataform.entity.enums.Rol;
import org.virtualizat.one.plataform.repository.ModuleRepository;
import org.virtualizat.one.plataform.repository.RoleRepository;
import org.virtualizat.one.plataform.service.AppService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public Role createRole(Role role) {
        Optional<Role> existingRole = roleRepository.findByRol(role.getRol());
        if (existingRole.isPresent()) {
            throw new RuntimeException("Role with name" + role.getRol().name() + " already exists.");
        }
        return roleRepository.save(role);
    }

    @Override
    public Module createModule(Module module) {
        if (moduleRepository.findByName(module.getName()).isPresent()) {
            throw new IllegalArgumentException("Module con nombre " + module.getName() + " realmente existe.");
        }
        if(module.getOptions()!= null) {
            for (Option option : module.getOptions()) {
                option.setModule(module);
            }
        }
        // Obtener el rol ROOT
        Role rootRole = roleRepository.findByRol(Rol.ROOT)
                .orElseThrow(() -> new IllegalArgumentException("Se debe crear el rol ROOT"));

        module.getRoles().add(rootRole);

        return moduleRepository.save(module);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public List<Module> getAllModule() {
        return moduleRepository.findAll();
    }

    @Override
    public Role getRole(UUID id) {
        return roleRepository.getReferenceById(id);
    }

    @Override
    public Module getModule(UUID id) {
        return moduleRepository.getReferenceById(id);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Module updateModule(UUID id, Module moduleDetails) {
        Module module = moduleRepository.findById(id).orElse(null);
        if (module != null) {
            module.setName(moduleDetails.getName());
            module.setVersion(moduleDetails.getVersion());
            module.setDescription(moduleDetails.getDescription());
            module.setCategory(moduleDetails.getCategory());
            module.setOptions(moduleDetails.getOptions());

            return moduleRepository.save(module);
        }
        return null;
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteModule(Module module) {
        moduleRepository.delete(module);
    }
}
