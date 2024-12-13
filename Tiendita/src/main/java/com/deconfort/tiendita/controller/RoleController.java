package com.deconfort.tiendita.controller;

import com.deconfort.tiendita.entity.Product;
import com.deconfort.tiendita.entity.Role;
import com.deconfort.tiendita.service.RoleService;
import com.deconfort.tiendita.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tiendita")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role/{name}")
    public ResponseEntity<Role> listarRolPorNombre(@PathVariable String name){
        Role role = roleService.verRole(name);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> listarRoles(){
        List<Role> roleList = roleService.verTodosLosRoles();
        return ResponseEntity.status(HttpStatus.OK).body(roleList);
    }

}
