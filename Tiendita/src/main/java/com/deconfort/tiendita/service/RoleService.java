package com.deconfort.tiendita.service;

import com.deconfort.tiendita.entity.Role;

import java.util.List;

public interface RoleService {

    Role verRole(String nameRole);
    List<Role> verTodosLosRoles();

}