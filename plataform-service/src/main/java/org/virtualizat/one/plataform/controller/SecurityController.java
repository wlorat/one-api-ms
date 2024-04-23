package org.virtualizat.one.plataform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.virtualizat.one.plataform.entity.User;
import org.virtualizat.one.plataform.service.SecurityService;

import java.util.List;

@RestController
@RequestMapping(value="/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @PostMapping(value="/users/create")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        try {
            User userCreate = securityService.createUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreate);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping(value="listUser")
    public ResponseEntity<List<User>> listUser(){
        List<User> users = securityService.getAllUser();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

}
