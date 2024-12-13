package com.deconfort.tiendita.service.impl;

import com.deconfort.tiendita.entity.Role;
import com.deconfort.tiendita.entity.UserEntity;
import com.deconfort.tiendita.repository.RoleRepository;
import com.deconfort.tiendita.repository.UserRepository;
import com.deconfort.tiendita.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserEntity registerUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();

            Role role = roleRepository.getById(1);

            role.addUser(user);
            roles.add(role);
            user.addRole(role);
            roleRepository.save(role);

        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new RuntimeException());
    }

    @Override
    public UserEntity deleteUser(String username){
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("No se encontro el usuario a eliminar"));
        userRepository.delete(user);
        return user;
    }
}
