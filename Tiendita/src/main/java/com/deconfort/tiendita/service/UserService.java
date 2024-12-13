package com.deconfort.tiendita.service;

import com.deconfort.tiendita.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    UserEntity findByUsername(String username);
    UserEntity registerUser(UserEntity user);

    UserEntity deleteUser(String username);
}
