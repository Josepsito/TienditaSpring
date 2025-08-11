package com.deconfort.tiendita.controller;

import com.deconfort.tiendita.entity.Role;
import com.deconfort.tiendita.entity.UserEntity;
import com.deconfort.tiendita.repository.RoleRepository;
import com.deconfort.tiendita.repository.UserRepository;
import com.deconfort.tiendita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tiendita")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try{
            UserEntity user = userService.findByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nombre de usuario no encontrado");
        }
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
        try{
            UserEntity user = userService.findByUsername(username);
            userService.deleteUser(user.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

