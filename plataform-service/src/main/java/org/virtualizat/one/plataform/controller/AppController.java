package org.virtualizat.one.plataform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.virtualizat.one.plataform.entity.Module;
import org.virtualizat.one.plataform.entity.Role;
import org.virtualizat.one.plataform.service.AppService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping(value="/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") UUID id){
        Role role = appService.getRole(id);
        if(null == role){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    @GetMapping(value="/roles")
    public ResponseEntity<List<Role>> listRole(){
        List<Role> rols = appService.getAllRole();
        if(rols.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rols);
    }

    @PostMapping(value="/roles")
    public ResponseEntity<?> createRole(@RequestBody Role role){
        try{
            Role newRole = appService.createRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(value="/modules")
    public ResponseEntity<?> createModule(@RequestBody Module module){
        try{
            Module newModule = appService.createModule(module);
            return ResponseEntity.status(HttpStatus.CREATED).body(newModule);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }


}
