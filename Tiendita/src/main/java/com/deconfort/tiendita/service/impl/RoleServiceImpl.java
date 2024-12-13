package com.deconfort.tiendita.service.impl;

import com.deconfort.tiendita.entity.Role;
import com.deconfort.tiendita.repository.RoleRepository;
import com.deconfort.tiendita.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role verRole(String nameRole) {
        return roleRepository.findByName(nameRole).orElseThrow(()->new RuntimeException("No se encontro el rol"));
    }

    @Override
    public List<Role> verTodosLosRoles() {
        return roleRepository.findAll();
    }
}
